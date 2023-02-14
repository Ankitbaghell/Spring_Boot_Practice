package com.hexaview.trs.userservice.adapter.persistence;

import com.hexaview.oms.userservice.Tables;
import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.UserCredential;
import com.hexaview.trs.userservice.port.persistence.UserCredentialRepository;
import java.sql.Connection;
import java.util.function.Function;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;

public class RdbmsUserCredentialRepository implements UserCredentialRepository {

  private final Datasource datasource;

  public RdbmsUserCredentialRepository(Datasource datasource) {
    this.datasource = datasource;
  }

  /** This functional mapper convert record fetch from db to UserCredential model */
  private final Function<Record, UserCredential> fromRecordToUserCredential =
      record -> {
        return new UserCredential.Builder(record.get(Tables.USER_CREDENTIAL.USER_ID_FK))
            .withId(record.get(Tables.USER_CREDENTIAL.ID))
            .withPassword(record.get(Tables.USER_CREDENTIAL.PASSWORD))
            .build();
      };

  @Override
  public UserCredential getByUsername(String username, DataSourceInfo dataSourceInfo) {
    UserCredential userCredential = null;
    try (Connection con =
        Datasource.getInstance(
                dataSourceInfo.getDatasourceUrl(),
                dataSourceInfo.getDatasourceUserName(),
                dataSourceInfo.getDatasourcePassword())
            .getConnection()) {
      DSLContext dsl = DSL.using(con);
      var record =
          dsl.select()
              .from(Tables.USER_CREDENTIAL)
              .where(
                  Tables.USER_CREDENTIAL.USER_ID_FK.eq(
                      (Long)
                          dsl.select()
                              .from(Tables.TRS_USER)
                              .where(Tables.TRS_USER.EMAIL.eq(username))
                              .fetchOne(Tables.TRS_USER.ID)))
              .fetchOne();
      con.close();
      userCredential = fromRecordToUserCredential.apply(record);
      con.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return userCredential;
  }
}
