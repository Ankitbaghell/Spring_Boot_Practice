package com.hexaview.trs.userservice.service.impl;

import com.hexaview.trs.userservice.model.AuthenticationInfo;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;
import com.hexaview.trs.userservice.model.User;
import com.hexaview.trs.userservice.model.UserCredential;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.service.IAuthenticateService;
import com.hexaview.trs.userservice.service.IAuthenticationService;
import com.hexaview.trs.userservice.service.ITokenService;
import com.hexaview.trs.userservice.service.IUserCredentialService;
import com.hexaview.trs.userservice.util.JwtTokenUtil;
import com.hexaview.trs.userservice.util.RoleUtil;
import http.request.AuthorizationFormRequest;

import java.util.Optional;

import static com.hexaview.trs.userservice.util.RoleUtil.ADMIN;

public class AuthenticationServiceImpl implements IAuthenticationService {

  private final ITokenService tokenService;
  private final IUserRepository userRepository;

  private final IAuthenticateService authenticateService;

  private final IUserCredentialService userCredentialService;

  private final JwtTokenUtil jwtTokenUtil;

  private final RoleUtil roleUtil;

  public AuthenticationServiceImpl(
          ITokenService tokenService, IUserRepository userRepository,
          IAuthenticateService authenticateService,
          IUserCredentialService userCredentialService,
          JwtTokenUtil jwtTokenUtil, RoleUtil roleUtil) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
    this.authenticateService = authenticateService;
    this.userCredentialService = userCredentialService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.roleUtil = roleUtil;
  }

  /**
   * @param username
   * @param password
   * @return
   */
  @Override
  public AuthenticationInfo authenticate(
      String username, String password, DataSourceInfo dataSourceInfo) {
    Optional<User> user = userRepository.getByUserName(username, dataSourceInfo);
    UserCredential userCredential =
        userCredentialService.getByUsername(user.get().getEmail(), dataSourceInfo);
    if (user.isEmpty() || user == null) {
      throw new RuntimeException("User Not Found");
    }

    Boolean valid = authenticateService.authenticatePassword(password, userCredential);

    if (!valid) {
      throw new RuntimeException("Invalid Credentials");
    }

    Token token = jwtTokenUtil.generateToken(user.get(),dataSourceInfo);
    if(token!=null){
      Long tokenId = tokenService.save(token, dataSourceInfo);
    }
    AuthenticationInfo authenticationInfo =
        new AuthenticationInfo.Builder(user.get().getUserUuid(), user.get().enableMFA())
            .withToken(token)
            .build();
    return authenticationInfo;
  }

  /**
   * @param authorizationFormRequest
   * @return
   */
  @Override
  public Boolean authorizeRole(AuthorizationFormRequest authorizationFormRequest) {
    String role = authorizationFormRequest.getRole();
    if(role.equals(ADMIN)){
        return true;
    }
    else{
      if(roleUtil.isIgnored(authorizationFormRequest.getUri())){
        return true;
      }else{
        return false;
      }
    }
  }


}
