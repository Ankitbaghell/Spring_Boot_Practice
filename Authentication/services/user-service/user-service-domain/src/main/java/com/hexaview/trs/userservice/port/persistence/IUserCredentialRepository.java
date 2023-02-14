package com.hexaview.trs.userservice.port.persistence;

import com.hexaview.trs.userservice.model.User;
import java.util.Optional;

public interface IUserCredentialRepository {

  Optional<User> getByUserName(String username);
}
