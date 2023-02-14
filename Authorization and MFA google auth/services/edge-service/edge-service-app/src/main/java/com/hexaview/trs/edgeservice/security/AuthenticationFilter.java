package com.hexaview.trs.edgeservice.security;

import static constant.EdgeServiceConstants.ACCESS_DENIED;

import com.hexaview.trs.constants.CommonConstants;
import com.hexaview.trs.edgeservice.api.Managementapi;
import com.hexaview.trs.edgeservice.api.Userapi;
import constant.EdgeServiceConstants;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import http.request.AuthorizationFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import http.response.ValidateTokenResponse;

@Component
public class AuthenticationFilter
    extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

  @Autowired private Managementapi managementapi;

  @Autowired private Userapi userApi;

  private Map<String, String> mapOfDomainNameAndTenantId = new HashMap<>();

  public AuthenticationFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();
      String subDomain = request.getHeaders().getOrEmpty("subdomain").get(0);
      String tenantId = null;
      if (mapOfDomainNameAndTenantId.containsKey(subDomain)) {
        tenantId = mapOfDomainNameAndTenantId.get(subDomain);
      } else {
        tenantId = managementapi.getOrgIdOfDomain(subDomain);
        mapOfDomainNameAndTenantId.put(subDomain, tenantId);
      }
      if (tenantId == null || tenantId.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, ACCESS_DENIED);
      }
      exchange.getRequest().mutate().header("tenantId", tenantId).build();
      final List<String> apiEndpoints = List.of(EdgeServiceConstants.IGNORE_END_POINTS);
      Predicate<ServerHttpRequest> isApiNotSecured =
          serverHttpRequest ->
              apiEndpoints.stream()
                  .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
      if (isApiNotSecured.test(request)) {
        if (!isAuthMissing(request)) {
          return this.onError(
              exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
        }

        final String requestTokenHeader = getAuthHeader(request);
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
          String jwtToken = requestTokenHeader.substring(7);
          ResponseEntity<ValidateTokenResponse> validateTokenResponse =
              userApi.validateJwt(jwtToken, tenantId);
          AuthorizationFormRequest authorizationFormRequest  = new AuthorizationFormRequest();
          authorizationFormRequest.setMapping(request.getMethod().toString());
          authorizationFormRequest.setRole(validateTokenResponse.getBody().getRole());
          authorizationFormRequest.setUri(request.getPath().toString());

          boolean isAuthorized = userApi.authorizationRole(authorizationFormRequest);
          if (validateTokenResponse != null
              && validateTokenResponse.getBody().getStatus().equals(CommonConstants.FAILURE)) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, validateTokenResponse.getBody().getMessage());
          }
          if (!isAuthorized) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ACCESS_DENIED);
          }
        }
      }
      return chain.filter(exchange.mutate().request(request).build());
    };
  }

  private String getAuthHeader(ServerHttpRequest request) {
    return request.getHeaders().getOrEmpty("Authorization").get(0);
  }

  private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(httpStatus);
    byte[] bytes = err.getBytes(StandardCharsets.UTF_8);
    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
    return exchange.getResponse().writeWith(Flux.just(buffer));
  }

  private boolean isAuthMissing(ServerHttpRequest request) {
    return request.getHeaders().containsKey("Authorization");
  }

  public static class Config {
    // Put the configuration properties
  }
}
