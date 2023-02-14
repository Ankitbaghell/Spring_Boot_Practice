package com.hexaview.trs.common.database.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class Datasource extends HikariDataSource {

  private Datasource(HikariConfig hikariConfig) {
    super(hikariConfig);
  }

  public static Datasource getInstance(final String url, final String user, final String password) {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(url);
    config.setUsername(user);
    config.setPassword(password);
    return new Datasource(config);
  }
}
