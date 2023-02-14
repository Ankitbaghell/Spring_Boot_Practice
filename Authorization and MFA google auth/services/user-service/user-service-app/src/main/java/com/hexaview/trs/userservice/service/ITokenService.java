package com.hexaview.trs.userservice.service;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;

public interface ITokenService {

  Long save(Token token, DataSourceInfo dataSourceInfo);

  Token getByToken(String token, DataSourceInfo dataSourceInfo);
}
