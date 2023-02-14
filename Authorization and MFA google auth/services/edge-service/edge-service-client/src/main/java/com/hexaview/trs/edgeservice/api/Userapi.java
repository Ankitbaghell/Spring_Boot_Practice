package com.hexaview.trs.edgeservice.api;

import constant.EdgeServiceConstants;
import http.request.AuthorizationFormRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import http.response.ValidateTokenResponse;

@FeignClient(name = "${service.user.name}", url = "${service.user.url}")
public interface Userapi {
  @PostMapping(value = EdgeServiceConstants.VALIDATE_TOKEN_URL)
  public ResponseEntity<ValidateTokenResponse> validateJwt(
      @RequestParam("token") String token, @RequestHeader(name = "tenantId") String tenantId);

  @PostMapping(value = EdgeServiceConstants.VALIDATE_ROLE)
  public Boolean authorizationRole(
          @RequestBody AuthorizationFormRequest authorizationFormRequest);
}
