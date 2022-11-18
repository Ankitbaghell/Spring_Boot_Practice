package com.employeeservice.EmployeeService.payload;

import com.employeeservice.EmployeeService.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service",url = "http://localhost:8080")
public interface EmpClient {

    @GetMapping("/customer/{empId}")
    public ResponseEntity<List<Customer>> getCustomer(@PathVariable int empId);
}
