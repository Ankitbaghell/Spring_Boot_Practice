package com.hexaview.trs.userservice.service;

import com.google.zxing.WriterException;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.User;
import http.request.AuthorizationFormRequest;
import http.request.LoginRequest;
import http.request.ValidateCodeRequest;
import http.response.AuthenicationInfoResponse;
import http.response.GetUserResponse;
import http.response.ValidateTokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public interface IUserService {

  User getById(Long id, DataSourceInfo dataSourceInfo);

  GetUserResponse getById(Long id);

  GetUserResponse getByUuid(String uuid, String tenantId);

  Optional<User> getByUserName(String username, DataSourceInfo dataSourceInfo);

  AuthenicationInfoResponse createAuthenticationToken(String tenantId, LoginRequest loginRequest);

  ValidateTokenResponse validateJwt(String token, String tenantId);

  Boolean authorizaRole(AuthorizationFormRequest authorizationFormRequest);

}
