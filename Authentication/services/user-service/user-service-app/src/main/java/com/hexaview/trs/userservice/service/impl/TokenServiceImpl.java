package com.hexaview.trs.userservice.service.impl;

import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;
import com.hexaview.trs.userservice.port.persistence.ITokenRepository;
import com.hexaview.trs.userservice.service.ITokenService;

public class TokenServiceImpl implements ITokenService {

  private ITokenRepository tokenRepository;

  public TokenServiceImpl(ITokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  /**
   * @param token
   * @return
   */
  @Override
  public Long save(Token token, DataSourceInfo dataSourceInfo) {
    return tokenRepository.save(token, dataSourceInfo);
  }

  @Override
  public Token getByToken(String token, DataSourceInfo dataSourceInfo) {
    return tokenRepository.getByToken(token, dataSourceInfo);
  }
}
