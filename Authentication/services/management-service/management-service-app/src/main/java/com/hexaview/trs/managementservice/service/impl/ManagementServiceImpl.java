package com.hexaview.trs.managementservice.service.impl;

import com.hexaview.trs.managementservice.model.DataSourceConfig;
import com.hexaview.trs.managementservice.port.persistence.ManagementRepository;
import com.hexaview.trs.managementservice.service.IManagementService;
import http.response.GetDataSourceConfigResponse;

import java.util.Map;
import java.util.function.Function;

public class ManagementServiceImpl implements IManagementService {

  private final ManagementRepository managementRepository;

  public ManagementServiceImpl(ManagementRepository managementRepository) {
    this.managementRepository = managementRepository;
  }

  private Function<DataSourceConfig, GetDataSourceConfigResponse>
          fromDataSourceConfigToGetDataSourceConfig =
          dataSourceConfig -> {
            return GetDataSourceConfigResponse.builder()
                    .driverClass(dataSourceConfig.getDriverClass())
                    .url(dataSourceConfig.getDatasourceUrl())
                    .email(dataSourceConfig.getDatasourceUserName())
                    .password(dataSourceConfig.getDatasourcePassword())
                    .dbName(dataSourceConfig.getDbName())
                    .host(dataSourceConfig.getHost())
                    .port(dataSourceConfig.getPort())
                    .tenantId(dataSourceConfig.getTenantId())
                    .build();
          };


  @Override
  public Map<String, DataSourceConfig> getAll(String datasourceType) {
    return managementRepository.getAll(datasourceType);
  }

  /**
   * @param orgId
   * @return
   */
  @Override
  public String getOrgIdOfDomain(String orgId) {
    return managementRepository.getOrgIdOfDomain(orgId);
  }

  /**
   * @param tenantId
   * @param datasourceType
   * @return
   */
  @Override
  public GetDataSourceConfigResponse getDatasourceConfigByTenantId(String tenantId, String datasourceType) {
    return fromDataSourceConfigToGetDataSourceConfig.apply(managementRepository.getDatasourceConfigByTenantId(tenantId, datasourceType)) ;
  }
}
