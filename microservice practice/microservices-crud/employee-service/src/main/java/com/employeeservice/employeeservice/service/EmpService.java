package com.employeeservice.employeeservice.service;

import com.employee_service.tables.pojos.Employee;
import com.employee_service.tables.pojos.Projects;
import com.employeeservice.employeeservice.client.EmpClient;
import com.employeeservice.employeeservice.model.EmployeeDto;
import com.employeeservice.employeeservice.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private EmpClient empClient;

    //get all emp
    public List<Employee> getAllEmp(){
        return empRepository.getAllEmp();
    }
    // get single emp **************
    public EmployeeDto getSingleEmp(int id){
        Employee emp = empRepository.getSingleEmp(id);
        Projects project = empClient.getSingleProject(emp.getPId());
        EmployeeDto employeeDto = new EmployeeDto(emp.getEmpid(), emp.getEmpname(), emp.getCity(), project);
        return employeeDto;
    }

    // create emp
    public void createEmp(Employee employee){
         empRepository.createEmp(employee);
    }
    //update emp
    public void updateEmp(Employee employee, int id){
        empRepository.updateEmp(employee, id);
    }
    //delete emp
    public void deleteEmp(int id){
        empRepository.deleteEmp(id);
    }
}
