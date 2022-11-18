package com.employeeservice.EmployeeService.controller;

import com.employeeservice.EmployeeService.entities.Employee;
import com.employeeservice.EmployeeService.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("/emp/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable int id){
        Employee emp = empService.getEmpById(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
