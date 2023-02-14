package com.hexaview.trs.userservice.service;

import com.hexaview.trs.userservice.model.AuthenticationInfo;
import com.hexaview.trs.userservice.model.DataSourceInfo;

public interface IAuthenticationService {
  AuthenticationInfo authenticate(String username, String password, DataSourceInfo dataSourceInfo);
}
