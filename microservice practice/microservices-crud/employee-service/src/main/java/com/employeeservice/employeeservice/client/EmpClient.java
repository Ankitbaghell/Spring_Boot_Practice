package com.employeeservice.employeeservice.client;

import com.employee_service.tables.pojos.Projects;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "project-service", url = "http://localhost:9090")
public interface EmpClient {

    @GetMapping("/project/{id}")
    public Projects getSingleProject(@PathVariable int id);

}
