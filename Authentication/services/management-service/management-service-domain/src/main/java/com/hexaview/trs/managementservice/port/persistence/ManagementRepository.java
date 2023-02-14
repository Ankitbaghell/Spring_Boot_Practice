package com.hexaview.trs.managementservice.port.persistence;

import com.hexaview.trs.managementservice.model.DataSourceConfig;
import java.util.Map;

public interface ManagementRepository {

  Map<String, DataSourceConfig> getAll(String datasourceType);

  String getOrgIdOfDomain(String domainName);

  DataSourceConfig getDatasourceConfigByTenantId(String tenantId, String datasourceType);
}
