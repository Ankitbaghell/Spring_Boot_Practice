package com.hexaview.trs.userservice.port.persistence;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;

public interface ITokenRepository {

  Long save(Token token, DataSourceInfo dataSourceInfo);

  Token getByToken(String token, DataSourceInfo dataSourceInfo);
}
