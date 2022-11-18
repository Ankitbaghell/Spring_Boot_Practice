package com.employeeservice.employeeservice.controller;

import com.employee_service.tables.pojos.Employee;
import com.employeeservice.employeeservice.model.EmployeeDto;
import com.employeeservice.employeeservice.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    //get ALl emp
    @GetMapping("/emp")
    public List<Employee> getAllEmp(){
        return empService.getAllEmp();
    }

    //get single emp
    @GetMapping("/emp/{id}")
    public EmployeeDto getSingleEmp(@PathVariable int id){
        return empService.getSingleEmp(id);
    }
    // create emp
    @PostMapping("/emp")
    public void createEmp(@RequestBody Employee employee){
        empService.createEmp(employee);
    }
    //update emp
    @PutMapping("/emp/{id}")
    public void updateEmp(@RequestBody Employee employee, @PathVariable int id){
        empService.updateEmp(employee, id);
    }
    //delete emp
    @DeleteMapping("/emp/{id}")
    public void deleteEmp(@PathVariable int id){
        empService.deleteEmp(id);
    }

}
