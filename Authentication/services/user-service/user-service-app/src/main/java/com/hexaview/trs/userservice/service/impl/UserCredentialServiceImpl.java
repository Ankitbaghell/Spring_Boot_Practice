package com.hexaview.trs.userservice.service.impl;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.UserCredential;
import com.hexaview.trs.userservice.port.persistence.UserCredentialRepository;
import com.hexaview.trs.userservice.service.IUserCredentialService;

public class UserCredentialServiceImpl implements IUserCredentialService {

  private UserCredentialRepository userCredentialRepository;

  public UserCredentialServiceImpl(UserCredentialRepository userCredentialRepository) {
    this.userCredentialRepository = userCredentialRepository;
  }

  @Override
  public UserCredential getByUsername(String username, DataSourceInfo dataSourceInfo) {
    return userCredentialRepository.getByUsername(username, dataSourceInfo);
  }
}
