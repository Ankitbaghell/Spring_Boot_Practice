package com.hexaview.trs.userservice.port.persistence;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.User;
import java.util.Optional;

public interface IUserRepository {

  User getById(Long id);

  User getByUuid(String id, DataSourceInfo dataSourceInfo);

  User getById(Long id, DataSourceInfo dataSourceInfo);

  Optional<User> getByUserName(String username, DataSourceInfo dataSourceInfo);

  Optional<User> getByUserName(String username);
}
