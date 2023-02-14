package com.hexaview.trs.userservice.adapter.persistence;

import com.hexaview.oms.userservice.Tables;
import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import com.hexaview.trs.userservice.model.User;
import com.hexaview.trs.userservice.port.persistence.IUserRepository;
import java.sql.Connection;
import java.util.Optional;
import java.util.function.Function;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;

public class RdbmsUserRepository implements IUserRepository {

  private Datasource datasource;

  public RdbmsUserRepository(Datasource datasource) {
    this.datasource = datasource;
  }

  private Function<Record, User> fromRecordToUser =
      record -> {
        return new User.Builder(
                record.get(Tables.TRS_USER.FIRST_NAME),
                record.get(Tables.TRS_USER.LAST_NAME),
                record.get(Tables.TRS_USER.EMAIL))
            .withEnableMFA(record.get(Tables.TRS_USER.ENABLE_MFA))
            .withUserUUID(record.get(Tables.TRS_USER.USER_UUID))
            .withId(record.get(Tables.TRS_USER.ID))
            .build();
      };

  private final Function<Record, User> fromRecordToUserDec =
      record -> {
        return new User.Builder(
                (String) record.get(Tables.TRS_USER.FIRST_NAME),
                (String) record.get(Tables.TRS_USER.LAST_NAME),
                (String) record.get(Tables.TRS_USER.EMAIL))
            .withId((Long) record.get(Tables.TRS_USER.ID))
            .withUserUUID((String) record.get(Tables.TRS_USER.USER_UUID))
            .withIsActive((boolean) record.get(Tables.TRS_USER.IS_ACTIVE))
            .build();
      };

  /**
   * @param username
   * @return
   */
  @Override
  public Optional<User> getByUserName(String username) {
    try (Connection conn = datasource.getConnection()) {
      DSLContext dsl = DSL.using(conn);
      var trsUserRecord =
          dsl.select().from(Tables.TRS_USER).where(Tables.TRS_USER.EMAIL.eq(username)).fetchOne();
      if (trsUserRecord == null) {
        return Optional.ofNullable(null);
      }
      conn.close();
      return Optional.ofNullable(fromRecordToUser.apply(trsUserRecord));
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public Optional<User> getByUserName(String username, DataSourceInfo dataSourceInfo) {
    try (Connection conn =
        Datasource.getInstance(
                dataSourceInfo.getDatasourceUrl(),
                dataSourceInfo.getDatasourceUserName(),
                dataSourceInfo.getDatasourcePassword())
            .getConnection()) {
      DSLContext dsl = DSL.using(conn);
      var trsUserRecord =
          dsl.select().from(Tables.TRS_USER).where(Tables.TRS_USER.EMAIL.eq(username)).fetchOne();
      if (trsUserRecord == null) {
        return Optional.ofNullable(null);
      }
      conn.close();
      return Optional.ofNullable(fromRecordToUser.apply(trsUserRecord));
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * + Method is used to get user data by id from the database
   *
   * @param id
   * @return User from the database
   */
  @Override
  public User getById(Long id) {
    User user = null;

    try (Connection con = datasource.getConnection()) {
      DSLContext dsl = DSL.using(con);
      var record =
          dsl.select()
              .from(Tables.TRS_USER)
              .where(Tables.TRS_USER.ID.eq(id))
              .and(Tables.TRS_USER.IS_ACTIVE.eq(true))
              .fetchOne();
      con.close();
      user = fromRecordToUserDec.apply(record);

    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    return user;
  }

  @Override
  public User getById(Long id, DataSourceInfo dataSourceInfo) {
    User user = null;

    try (Connection con =
        Datasource.getInstance(
                dataSourceInfo.getDatasourceUrl(),
                dataSourceInfo.getDatasourceUserName(),
                dataSourceInfo.getDatasourcePassword())
            .getConnection()) {
      DSLContext dsl = DSL.using(con);
      var record =
          dsl.select()
              .from(Tables.TRS_USER)
              .where(Tables.TRS_USER.ID.eq(id))
              .and(Tables.TRS_USER.IS_ACTIVE.eq(true))
              .fetchOne();
      con.close();
      user = fromRecordToUserDec.apply(record);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    return user;
  }

  public User getByUuid(String id, DataSourceInfo dataSourceInfo) {
    User user = null;

    try (Connection con =
        Datasource.getInstance(
                dataSourceInfo.getDatasourceUrl(),
                dataSourceInfo.getDatasourceUserName(),
                dataSourceInfo.getDatasourcePassword())
            .getConnection()) {
      DSLContext dsl = DSL.using(con);
      var record =
          dsl.select()
              .from(Tables.TRS_USER)
              .where(Tables.TRS_USER.USER_UUID.eq(id))
              .and(Tables.TRS_USER.IS_ACTIVE.eq(true))
              .fetchOne();
      con.close();
      user = fromRecordToUserDec.apply(record);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    return user;
  }
}
