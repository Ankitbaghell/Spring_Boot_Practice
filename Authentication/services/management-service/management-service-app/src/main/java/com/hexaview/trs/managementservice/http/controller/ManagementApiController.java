package com.hexaview.trs.managementservice.http.controller;

import com.hexaview.trs.managementservice.service.IManagementService;
import constant.ManagementServiceConstant;
import http.response.GetDataSourceConfigResponse;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ManagementServiceConstant.BASE_URL)
public class ManagementApiController {

    private final IManagementService managementService;

    public ManagementApiController(IManagementService managementService) {
        this.managementService = managementService;
    }


    /**
     * Method is used to fetch tenantId from organizationInfo
     *
     * @param orgId
     * @return String
     */
    @GetMapping(value = ManagementServiceConstant.GET_ORG_ID)
    public String getOrgIdOfDomain(@PathVariable("orgId") String orgId) {
        var result = managementService.getOrgIdOfDomain(orgId);
        return result;
    }

    @GetMapping(value = ManagementServiceConstant.GET_DATASOURCE_CONFIG)
    public ResponseEntity<GetDataSourceConfigResponse> getDatasourceConfig(
            @RequestParam String tenantId, @RequestParam String datasourceType) {
        var result = managementService.getDatasourceConfigByTenantId(tenantId, datasourceType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
