package com.hexaview.trs.userservice.service.impl;

import com.hexaview.trs.userservice.model.UserCredential;
import com.hexaview.trs.userservice.service.IAuthenticateService;

public class AuthenticateServiceImpl implements IAuthenticateService {

  /**
   * @param password
   * @param userCredential
   * @return
   */
  @Override
  public Boolean authenticatePassword(String password, UserCredential userCredential) {
    return password.equals(userCredential.getPassword());
  }
}
