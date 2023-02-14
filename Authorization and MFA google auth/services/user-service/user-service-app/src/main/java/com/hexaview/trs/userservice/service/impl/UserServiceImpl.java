package com.hexaview.trs.userservice.service.impl;

import com.hexaview.trs.constants.CommonConstants;
import com.hexaview.trs.userservice.model.AuthenticationInfo;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;
import com.hexaview.trs.userservice.model.User;
import com.hexaview.trs.userservice.model.ValidateToken;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.service.IAuthenticationService;
import com.hexaview.trs.userservice.service.ITokenService;
import com.hexaview.trs.userservice.service.IUserService;
import com.hexaview.trs.userservice.util.DataSourceCachingUtil;
import com.hexaview.trs.userservice.util.JwtTokenUtil;
import constant.TokenConstants;
import http.request.AuthorizationFormRequest;
import http.request.LoginRequest;
import http.response.AuthenicationInfoResponse;
import http.response.GetUserResponse;
import http.response.UserResponse;
import http.response.ValidateTokenResponse;

import java.util.Optional;
import java.util.function.Function;

public class UserServiceImpl implements IUserService {

   private  final JwtTokenUtil jwtTokenUtil;

   private  final IAuthenticationService authenticationService;

   private final ITokenService tokenService;

  private final IUserRepository userRepository;

  private final DataSourceCachingUtil dataSourceCachingUtil;

  public UserServiceImpl(JwtTokenUtil jwtTokenUtil, IAuthenticationService authenticationService, ITokenService tokenService, IUserRepository userRepository, DataSourceCachingUtil dataSourceCachingUtil) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.authenticationService = authenticationService;
    this.tokenService = tokenService;
    this.userRepository = userRepository;
    this.dataSourceCachingUtil = dataSourceCachingUtil;
  }

  private final Function<User, GetUserResponse> fromUserToGetResponse =
      user -> {
        Long id = user.getId() != null ? user.getId() : null;
        return GetUserResponse.builder()
            .id(id)
            .username(user.getEmail())
            .userUuid(user.getUserUuid())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
      };

  private final Function<User, UserResponse> fromUsertoUserResponse =
      user -> {
        if (user == null) return UserResponse.builder().build();
        return UserResponse.builder()
            .id(user.getId())
            .username(user.getEmail())
            .isActive(user.getIsActive())
            .build();
      };


//  private DataSourceInfo getDataSourceInfo(String tenantId) {
//    GetDataSourceConfigResponse getDataSourceConfigResponse = null;
//    if (mapOfTenantIdAndDataSourceConfigResponse.containsKey(tenantId)) {
//      getDataSourceConfigResponse = mapOfTenantIdAndDataSourceConfigResponse.get(tenantId);
//    } else {
//      getDataSourceConfigResponse =
//          managementapi.getDatasourceConfig(tenantId, "postgresDb").getBody();
//      mapOfTenantIdAndDataSourceConfigResponse.put(tenantId, getDataSourceConfigResponse);
//    }
//    return fromGetDataSourceResponseToDataSourceInfo.apply(getDataSourceConfigResponse);
//  }

  private final Function<ValidateToken, ValidateTokenResponse>
      fromValidateTokenToValidateTokenResponse =
          validateToken -> {
            return ValidateTokenResponse.builder()
                .status(validateToken.getStatus())
                .message(validateToken.getMessage())
                .role(validateToken.getRole())
                .userResponse(fromUsertoUserResponse.apply(validateToken.getUser()))
                .build();
          };

  private Function<AuthenticationInfo, AuthenicationInfoResponse>
      fromAuthenticationInfoToAuthenticationInfoResponse =
          authenticationInfo -> {
            return AuthenicationInfoResponse.builder()
                .userUuid(authenticationInfo.getUserUuid())
                .token(
                    authenticationInfo.getToken() == null
                        ? null
                        : authenticationInfo.getToken().getToken())
                .mfaStatus(authenticationInfo.getMfaStatus())
                .build();
          };

  @Override
  public User getById(Long id, DataSourceInfo dataSourceInfo) {
    return userRepository.getById(id, dataSourceInfo);
  }

  @Override
  public GetUserResponse getById(Long id) {
    return fromUserToGetResponse.apply(userRepository.getById(id));
  }

  @Override
  public GetUserResponse getByUuid(String uuid, String tenantId) {
    DataSourceInfo dataSourceInfo = dataSourceCachingUtil.getDataSourceInfo(tenantId);
    return fromUserToGetResponse.apply(userRepository.getByUuid(uuid, dataSourceInfo));
  }
  /**
   * @param username
   * @return
   */
  @Override
  public Optional<User> getByUserName(String username, DataSourceInfo dataSourceInfo) {
    return userRepository.getByUserName(username, dataSourceInfo);
  }

  @Override
  public AuthenicationInfoResponse createAuthenticationToken(
      String tenantId, LoginRequest loginRequest) {
    DataSourceInfo dataSourceInfo = dataSourceCachingUtil.getDataSourceInfo(tenantId);
    AuthenticationInfo authenticationInfo =
        authenticationService.authenticate(
            loginRequest.getUsername(), loginRequest.getPassword(), dataSourceInfo);
    return fromAuthenticationInfoToAuthenticationInfoResponse.apply(authenticationInfo);
  }

  @Override
  public ValidateTokenResponse validateJwt(String token, String tenantId) {
    DataSourceInfo dataSourceInfo = dataSourceCachingUtil.getDataSourceInfo(tenantId);
    ValidateToken validateToken = null;
    String username = jwtTokenUtil.getUsernameFromToken(token);
    if (username.equals(TokenConstants.INVALID_TOKEN)) {
      validateToken =
          new ValidateToken.Builder(CommonConstants.FAILURE, TokenConstants.INVALID_TOKEN_SIGNATURE)
              .build();

      return fromValidateTokenToValidateTokenResponse.apply(validateToken);
    }
    Optional<User> user = getByUserName(username, dataSourceInfo);
    Token tokenFromDb = tokenService.getByToken(token, dataSourceInfo);
    if (user != null && tokenFromDb != null) {

      if (user.get().getEmail().equals(username)
          && user.get().getIsActive()
          && !jwtTokenUtil.isTokenExpired(token)) {
        validateToken =
            new ValidateToken.Builder(TokenConstants.SUCCESS, TokenConstants.TOKEN_VERIFIED)
                .withUser(user.get())
                    .withRole(jwtTokenUtil.getUserRoleFromToken(tokenFromDb.getToken()))
                .build();
      }

    } else {
      throw new RuntimeException("User not found from token");
    }
    ValidateTokenResponse validateTokenResponse =
        fromValidateTokenToValidateTokenResponse.apply(validateToken);
    return validateTokenResponse;
  }

  /**
   * @param authorizationFormRequest
   * @return
   */
  @Override
  public Boolean authorizaRole(AuthorizationFormRequest authorizationFormRequest) {
   return authenticationService.authorizeRole(authorizationFormRequest);
  }
}
