package com.hexaview.trs.userservice.port.persistence;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.UserCredential;

public interface UserCredentialRepository {
  UserCredential getByUsername(String username, DataSourceInfo dataSourceInfo);
}
