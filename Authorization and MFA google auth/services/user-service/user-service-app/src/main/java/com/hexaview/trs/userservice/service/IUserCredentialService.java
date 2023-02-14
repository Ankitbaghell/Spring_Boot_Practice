package com.hexaview.trs.userservice.service;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.UserCredential;

public interface IUserCredentialService {

  UserCredential getByUsername(String username, DataSourceInfo dataSourceInfo);
}
