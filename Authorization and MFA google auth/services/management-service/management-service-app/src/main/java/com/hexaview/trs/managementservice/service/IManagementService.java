package com.hexaview.trs.managementservice.service;

import com.hexaview.trs.managementservice.model.DataSourceConfig;
import http.response.GetDataSourceConfigResponse;

import java.util.Map;

public interface IManagementService {

  Map<String, DataSourceConfig> getAll(String datasourceType);

  String getOrgIdOfDomain(String orgId);

  GetDataSourceConfigResponse getDatasourceConfigByTenantId(String tenantId, String datasourceType);
}
