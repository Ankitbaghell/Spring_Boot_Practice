package com.hexaview.trs.userservice.util;

import com.hexaview.trs.userservice.Managementapi;
import com.hexaview.trs.userservice.model.DataSourceInfo;
import http.response.GetDataSourceConfigResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DataSourceCachingUtil {

    private final Map<String, GetDataSourceConfigResponse> mapOfTenantIdAndDataSourceConfigResponse;

    private  final Managementapi managementapi;

    public DataSourceCachingUtil(Managementapi managementapi) {
        this.managementapi = managementapi;
        this.mapOfTenantIdAndDataSourceConfigResponse = new HashMap<>();
    }


    private final Function<GetDataSourceConfigResponse, DataSourceInfo>
            fromGetDataSourceResponseToDataSourceInfo =
            getDataSourceConfigResponse -> {
                return new DataSourceInfo.Builder(
                        getDataSourceConfigResponse.getTenantId(),
                        "postgresDb",
                        getDataSourceConfigResponse.getEmail(),
                        getDataSourceConfigResponse.getPassword(),
                        getDataSourceConfigResponse.getHost(),
                        getDataSourceConfigResponse.getPort(),
                        getDataSourceConfigResponse.getDbName())
                        .withDatasourceUrl(getDataSourceConfigResponse.getUrl())
                        .build();
            };


    public DataSourceInfo getDataSourceInfo(String tenantId) {
        GetDataSourceConfigResponse getDataSourceConfigResponse = null;
        if (mapOfTenantIdAndDataSourceConfigResponse.containsKey(tenantId)) {
            getDataSourceConfigResponse = mapOfTenantIdAndDataSourceConfigResponse.get(tenantId);
        } else {
            getDataSourceConfigResponse =
                    managementapi.getDatasourceConfig(tenantId, "postgresDb").getBody();
            mapOfTenantIdAndDataSourceConfigResponse.put(tenantId, getDataSourceConfigResponse);
        }
        return fromGetDataSourceResponseToDataSourceInfo.apply(getDataSourceConfigResponse);
    }
}
