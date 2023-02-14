package com.hexaview.trs.userservice.service;

import com.hexaview.trs.userservice.model.UserCredential;

public interface IAuthenticateService {

  Boolean authenticatePassword(String password, UserCredential userCredential);
}
