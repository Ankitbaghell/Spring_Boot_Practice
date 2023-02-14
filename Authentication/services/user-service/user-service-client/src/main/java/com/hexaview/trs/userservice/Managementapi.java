package com.hexaview.trs.userservice;

import constant.UserServiceConstants;
import http.response.GetDataSourceConfigResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${service.management.name}", url = "${service.management.url}")
public interface Managementapi {
  @GetMapping(value = UserServiceConstants.GET_DATASOURCE_CONFIG)
  public ResponseEntity<GetDataSourceConfigResponse> getDatasourceConfig(
      @RequestParam("tenantId") String tenantId,
      @RequestParam("datasourceType") String datasourceType);
}
