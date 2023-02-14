package com.hexaview.trs.managementservice.adapter.persistence;

import static com.hexaview.trs.constants.CommonConstants.POSTGRES_DB;

import com.hexaview.oms.managementservice.Tables;
import com.hexaview.trs.common.database.connection.Datasource;
import com.hexaview.trs.managementservice.model.DataSourceConfig;
import com.hexaview.trs.managementservice.port.persistence.ManagementRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

public class ManagementRdbmsRepository implements ManagementRepository {

  private final Datasource datasource;

  private Map<String, DataSourceConfig> mapOfTenantIdAndPostgresConfig;

  private Map<String, DataSourceConfig> mapOfTenantIdAndMongoConfig;

  public ManagementRdbmsRepository(Datasource datasource) {
    this.datasource = datasource;
    mapOfTenantIdAndPostgresConfig = new HashMap<>();
    mapOfTenantIdAndMongoConfig = new HashMap<>();
  }

  /* mapper function for convert record to map of data source */
  private Function<Result<Record>, Map<String, DataSourceConfig>> fromResultOfRecordToMapOfDetails =
      result -> {
        Map<String, DataSourceConfig> mapOfDetails = new HashMap<>();
        result.forEach(
            record -> {
              DataSourceConfig dataSourceConfig =
                  new DataSourceConfig.Builder(
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.TENANT_ID),
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_TYPE),
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_USERNAME),
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_PASSWORD),
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.HOST),
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.PORT),
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.DB_NAME))
                      .withDriverClass(record.get(Tables.ORGANIZATIONDATASOURCEINFO.DRIVER_CLASS))
                      .withDatasourceUrl(
                          record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_URL))
                      .build();
              mapOfDetails.put(
                  record.get(Tables.ORGANIZATIONDATASOURCEINFO.TENANT_ID), dataSourceConfig);
              if (record
                  .get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_TYPE)
                  .equals(POSTGRES_DB)) {
                mapOfTenantIdAndPostgresConfig.put(
                    record.get(Tables.ORGANIZATIONDATASOURCEINFO.TENANT_ID), dataSourceConfig);
              } else {
                mapOfTenantIdAndMongoConfig.put(
                    record.get(Tables.ORGANIZATIONDATASOURCEINFO.TENANT_ID), dataSourceConfig);
              }
            });
        return mapOfDetails;
      };

  private Function<Record, DataSourceConfig> fromRecordToDatasource =
      record -> {
        return new DataSourceConfig.Builder(
                record.get(Tables.ORGANIZATIONDATASOURCEINFO.TENANT_ID),
                record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_TYPE),
                record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_USERNAME),
                record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_PASSWORD),
                record.get(Tables.ORGANIZATIONDATASOURCEINFO.HOST),
                record.get(Tables.ORGANIZATIONDATASOURCEINFO.PORT),
                record.get(Tables.ORGANIZATIONDATASOURCEINFO.DB_NAME))
            .withDriverClass(record.get(Tables.ORGANIZATIONDATASOURCEINFO.DRIVER_CLASS))
            .withDatasourceUrl(record.get(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_URL))
            .build();
      };

  /**
   * + Method used to get all tenant data source from database
   *
   * @param datasourceType(postgres or mongo)
   * @return map of all tenant data source
   */
  @Override
  public Map<String, DataSourceConfig> getAll(String datasourceType) {
    try (Connection con = datasource.getConnection()) {

      DSLContext dslContext = DSL.using(con);
      var result =
          dslContext
              .select()
              .from(Tables.ORGANIZATIONDATASOURCEINFO)
              .where(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_TYPE.eq(datasourceType))
              .fetch();
      return fromResultOfRecordToMapOfDetails.apply(result);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      throw new RuntimeException(sqlException);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
      throw new RuntimeException(dataAccessException);
    }
  }

  /**
   * @param domainName
   * @return
   */
  @Override
  public String getOrgIdOfDomain(String domainName) {
    try (Connection con = datasource.getConnection()) {
      DSLContext dsl = DSL.using(con);
      var result =
          dsl.select()
              .from(Tables.ORGANIZATIONINFO)
              .where(Tables.ORGANIZATIONINFO.DOMAIN_NAME.eq(domainName))
              .fetchOne();
      return result.get(Tables.ORGANIZATIONINFO.TENANT_ID);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      throw new RuntimeException(sqlException);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
      throw new RuntimeException(dataAccessException);
    }
  }

  /**
   * @param tenantId
   * @param datasourceType
   * @return
   */
  @Override
  public DataSourceConfig getDatasourceConfigByTenantId(String tenantId, String datasourceType) {
    DataSourceConfig dataSourceConfig;
    if (datasourceType.equals(POSTGRES_DB)) {
      dataSourceConfig = mapOfTenantIdAndPostgresConfig.get(tenantId);
    } else {
      dataSourceConfig = mapOfTenantIdAndMongoConfig.get(tenantId);
    }

    if (dataSourceConfig == null) {
      try (Connection con = datasource.getConnection()) {
        DSLContext dslContext = DSL.using(con);
        var result =
            dslContext
                .select()
                .from(Tables.ORGANIZATIONDATASOURCEINFO)
                .where(Tables.ORGANIZATIONDATASOURCEINFO.TENANT_ID.eq(tenantId))
                .and(Tables.ORGANIZATIONDATASOURCEINFO.DATASOURCE_TYPE.eq(datasourceType))
                .fetchOne();
        var newDataSourceConfig = fromRecordToDatasource.apply(result);
        if (newDataSourceConfig.getDatasourceType().equals(POSTGRES_DB)) {
          mapOfTenantIdAndPostgresConfig.put(tenantId, newDataSourceConfig);
        } else {
          mapOfTenantIdAndMongoConfig.put(tenantId, newDataSourceConfig);
        }
        return newDataSourceConfig;
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw new RuntimeException(sqlException);
      } catch (DataAccessException dataAccessException) {
        dataAccessException.printStackTrace();
        throw new RuntimeException(dataAccessException);
      }
    }
    return dataSourceConfig;
  }
}
