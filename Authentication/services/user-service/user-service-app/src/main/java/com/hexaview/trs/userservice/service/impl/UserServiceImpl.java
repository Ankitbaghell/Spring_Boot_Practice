package com.hexaview.trs.userservice.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hexaview.trs.constants.CommonConstants;
import com.hexaview.trs.userservice.Managementapi;
import com.hexaview.trs.userservice.model.AuthenticationInfo;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;
import com.hexaview.trs.userservice.model.User;
import com.hexaview.trs.userservice.model.ValidateToken;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.service.IAuthenticationService;
import com.hexaview.trs.userservice.service.ITokenService;
import com.hexaview.trs.userservice.service.IUserService;
import com.hexaview.trs.userservice.util.JwtTokenUtil;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import constant.TokenConstants;
import http.request.LoginRequest;
import http.request.ValidateCodeRequest;
import http.response.AuthenicationInfoResponse;
import http.response.GetDataSourceConfigResponse;
import http.response.GetUserResponse;
import http.response.UserResponse;
import http.response.ValidateCodeResponse;
import http.response.ValidateTokenResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {

  @Autowired private GoogleAuthenticator gAuth;

   private  JwtTokenUtil jwtTokenUtil;

   private  IAuthenticationService authenticationService;

   private  ITokenService tokenService;
  private  Managementapi managementapi;

  public UserServiceImpl(GoogleAuthenticator gAuth, JwtTokenUtil jwtTokenUtil, IAuthenticationService authenticationService, ITokenService tokenService, Managementapi managementapi, Map<String, GetDataSourceConfigResponse> mapOfTenantIdAndDataSourceConfigResponse, IUserRepository userRepository) {
    this.gAuth = gAuth;
    this.jwtTokenUtil = jwtTokenUtil;
    this.authenticationService = authenticationService;
    this.tokenService = tokenService;
    this.managementapi = managementapi;
    this.mapOfTenantIdAndDataSourceConfigResponse = mapOfTenantIdAndDataSourceConfigResponse;
    this.userRepository = userRepository;
  }

  private Map<String, GetDataSourceConfigResponse> mapOfTenantIdAndDataSourceConfigResponse;

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

  private final Function<GetDataSourceConfigResponse, DataSourceInfo>
      fromGetDataSourceResponseToDataSourceInfo =
          getDataSourceConfigResponse -> {
            return new DataSourceInfo.Builder(
                    getDataSourceConfigResponse.getTenantId(),
                    "postgresDb",
                    getDataSourceConfigResponse.getEmail(),
                    getDataSourceConfigResponse.getPassword(),
                    getDataSourceConfigResponse.getHost(),
                    getDataSourceConfigResponse.getPort(),
                    getDataSourceConfigResponse.getDbName())
                .withDatasourceUrl(getDataSourceConfigResponse.getUrl())
                .build();
          };

  private IUserRepository userRepository;

  private DataSourceInfo getDataSourceInfo(String tenantId) {
    GetDataSourceConfigResponse getDataSourceConfigResponse = null;
    if (mapOfTenantIdAndDataSourceConfigResponse.containsKey(tenantId)) {
      getDataSourceConfigResponse = mapOfTenantIdAndDataSourceConfigResponse.get(tenantId);
    } else {
      getDataSourceConfigResponse =
          managementapi.getDatasourceConfig(tenantId, "postgresDb").getBody();
      mapOfTenantIdAndDataSourceConfigResponse.put(tenantId, getDataSourceConfigResponse);
    }
    return fromGetDataSourceResponseToDataSourceInfo.apply(getDataSourceConfigResponse);
  }

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

  public UserServiceImpl(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

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
    DataSourceInfo dataSourceInfo = getDataSourceInfo(tenantId);
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
    DataSourceInfo dataSourceInfo = getDataSourceInfo(tenantId);
    AuthenticationInfo authenticationInfo =
        authenticationService.authenticate(
            loginRequest.getUsername(), loginRequest.getPassword(), dataSourceInfo);
    if (authenticationInfo.getToken() != null) {
      Long tokenId = tokenService.save(authenticationInfo.getToken(), dataSourceInfo);
    }
    return fromAuthenticationInfoToAuthenticationInfoResponse.apply(authenticationInfo);
  }

  @Override
  public ValidateTokenResponse validateJwt(String token, String tenantId) {
    DataSourceInfo dataSourceInfo = getDataSourceInfo(tenantId);
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
                .build();
      }

    } else {
      throw new RuntimeException("User not found from token");
    }
    ValidateTokenResponse validateTokenResponse =
        fromValidateTokenToValidateTokenResponse.apply(validateToken);
    return validateTokenResponse;
  }

  @Override
  public void generate(String uuid, HttpServletResponse servletResponse, String tenantId)
      throws IOException, WriterException {
    GoogleAuthenticatorKey key = gAuth.createCredentials(uuid);

    QRCodeWriter qrCodeWriter = new QRCodeWriter();

    String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(tenantId, uuid, key);

    BitMatrix bitMatrix = qrCodeWriter.encode(otpAuthURL, BarcodeFormat.QR_CODE, 200, 200);

    ServletOutputStream outputStream = servletResponse.getOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
    outputStream.close();
  }

  @Override
  public ValidateCodeResponse validateKey(
      ValidateCodeRequest validateCodeRequest, String tenantId) {
    DataSourceInfo dataSourceInfo = getDataSourceInfo(tenantId);
    Token token =
        jwtTokenUtil.generateToken(userRepository.getByUuid(validateCodeRequest.getUserUuid(), dataSourceInfo));
    if (token.getToken() != null) {
      Long tokenId = tokenService.save(token, dataSourceInfo);
    }
    return ValidateCodeResponse.builder()
        .token(token.getToken())
        .isValidCode(
            gAuth.authorizeUser(validateCodeRequest.getUserUuid(), validateCodeRequest.getCode()))
        .build();
  }
}
