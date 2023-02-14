package com.hexaview.trs.userservice.service.impl;

import com.hexaview.trs.userservice.model.AuthenticationInfo;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;
import com.hexaview.trs.userservice.model.User;
import com.hexaview.trs.userservice.model.UserCredential;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import com.hexaview.trs.userservice.service.IAuthenticateService;
import com.hexaview.trs.userservice.service.IAuthenticationService;
import com.hexaview.trs.userservice.service.IUserCredentialService;
import com.hexaview.trs.userservice.util.JwtTokenUtil;
import java.util.Optional;

public class AuthenticationServiceImpl implements IAuthenticationService {

  private final IUserRepository userRepository;

  private final IAuthenticateService authenticateService;

  private final IUserCredentialService userCredentialService;

  private final JwtTokenUtil jwtTokenUtil;

  public AuthenticationServiceImpl(
      IUserRepository userRepository,
      IAuthenticateService authenticateService,
      IUserCredentialService userCredentialService,
      JwtTokenUtil jwtTokenUtil) {
    this.userRepository = userRepository;
    this.authenticateService = authenticateService;
    this.userCredentialService = userCredentialService;
    this.jwtTokenUtil = jwtTokenUtil;
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
    Token token = null;
    if (!user.get().enableMFA()) {
      token = jwtTokenUtil.generateToken(user.get());
    }
    AuthenticationInfo authenticationInfo =
        new AuthenticationInfo.Builder(user.get().getUserUuid(), user.get().enableMFA())
            .withToken(token)
            .build();
    return authenticationInfo;
  }
}
