package com.hexaview.trs.userservice.adapter.persistence;

import com.hexaview.oms.userservice.Tables;
import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.Token;
import com.hexaview.trs.userservice.port.persistence.ITokenRepository;
import java.sql.Connection;
import java.util.function.Function;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;

public class RdbmsTokenRepository implements ITokenRepository {

  private final Datasource datasource;

  public RdbmsTokenRepository(Datasource datasource) {
    this.datasource = datasource;
  }

  /** This functional mapper convert record fetch from db to Token model */
  private final Function<Record, Token> fromResultToToken =
      record -> {
        return new Token.Builder(
                record.get(Tables.TOKEN.TOKEN_),
                record.get(Tables.TOKEN.USER_ID_FK),
                record.get(Tables.TOKEN.TYPE))
            .withId(record.get(Tables.TOKEN.ID))
            .build();
      };

  @Override
  public Long save(Token token, DataSourceInfo dataSourceInfo) {
    Long result = 0l;
    try (Connection con =
        Datasource.getInstance(
                dataSourceInfo.getDatasourceUrl(),
                dataSourceInfo.getDatasourceUserName(),
                dataSourceInfo.getDatasourcePassword())
            .getConnection()) {
      DSLContext dsl = DSL.using(con);
      var record =
          dsl.insertInto(Tables.TOKEN)
              .set(Tables.TOKEN.TOKEN_, token.getToken())
              .set(Tables.TOKEN.TOKEN_UUID, token.getTokenUuid())
              .set(Tables.TOKEN.USER_ID_FK, token.getUserId())
              .set(Tables.TOKEN.CREATED_AT, token.getCreatedAt())
              .set(Tables.TOKEN.CREATED_BY, token.getCreatedBy())
              .set(Tables.TOKEN.UPDATED_AT, token.getUpdatedAt())
              .set(Tables.TOKEN.UPDATED_BY, token.getUpdatedBy())
              .returningResult(Tables.TOKEN.ID)
              .fetchOne();
      con.close();
      result = record.into(Long.class);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    return result;
  }

  @Override
  public Token getByToken(String token, DataSourceInfo dataSourceInfo) {
    Token result = null;
    try (Connection con =
        Datasource.getInstance(
                dataSourceInfo.getDatasourceUrl(),
                dataSourceInfo.getDatasourceUserName(),
                dataSourceInfo.getDatasourcePassword())
            .getConnection()) {
      DSLContext dsl = DSL.using(con);

      var record = dsl.select().from(Tables.TOKEN).where(Tables.TOKEN.TOKEN_.eq(token)).fetchOne();
      con.close();
      result = fromResultToToken.apply(record);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    return result;
  }
}
