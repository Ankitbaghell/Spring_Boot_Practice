package com.hexaview.trs.edgeservice.api;

import constant.EdgeServiceConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${service.management.name}", url = "${service.management.url}")
public interface Managementapi {

  @GetMapping(value = EdgeServiceConstants.GET_ORG_ID)
  public String getOrgIdOfDomain(@PathVariable("orgId") String orgId);
}
