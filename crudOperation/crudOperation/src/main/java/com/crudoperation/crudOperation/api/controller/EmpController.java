package com.crudoperation.crudOperation.api.controller;

import com.crudoperation.crudOperation.data.entities.Employee;
import com.crudoperation.crudOperation.data.mapper.EmpMapper;
import com.crudoperation.crudOperation.data.models.EmpModel;
import com.crudoperation.crudOperation.data.models.EmpRequest;
import com.crudoperation.crudOperation.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class EmpController {
    @Autowired
    private CrudService crudService;

    @Autowired
    private EmpMapper empMapper;

    @GetMapping("/api/user/")
    public List<Employee> getAllEmp(){
        List<EmpRequest> allEmp = crudService.getAllEmp();
        return empMapper.requestListToEmpList(allEmp);
    }

    @GetMapping("/api/user/{id}")
    public Employee getSingleEmp(@PathVariable("id") int id){
        EmpRequest emp = crudService.getEmp(id);
        return empMapper.empFromRequest(emp);
    }

    @PostMapping("/api/user")
    public Employee createEmp(@Valid @RequestBody EmpRequest employee){
//        if(employee == null || employee.getName().length() == 0 || employee.getJobTitle().length() == 0
//        || employee.getAge() <= 0){
//            //400
//            System.out.println("Please Enter Name , JobTitle And Age Correctly");
//            return null;
//        }

        EmpModel empModel = empMapper.requestToModel(employee);
        EmpRequest emp = crudService.createEmp(empModel);
        return empMapper.empFromRequest(emp);
    }

    @PutMapping("/api/user/{id}")
    public Employee updateEmp(@Valid @RequestBody EmpRequest employee, @PathVariable("id") int id){
        if(employee == null || employee.getName().length() == 0 || employee.getJobTitle().length() == 0
                || employee.getAge() <= 0){
            System.out.println("Please Enter Name , JobTitle And Age Correctly");
            return null;
        }
        employee.setEid(id);
        EmpModel empModel = empMapper.requestToModel(employee);
        EmpRequest emp = crudService.updateEmp(empModel,id);
        return empMapper.empFromRequest(emp);

    }

    @DeleteMapping("/api/user/{id}")
    public void deleteEmp(@PathVariable("id") int id){

       crudService.deleteEmp(id);
    }
}
