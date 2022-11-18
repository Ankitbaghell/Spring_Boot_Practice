package com.employeeservice.EmployeeService.service;

import com.employeeservice.EmployeeService.entities.Customer;
import com.employeeservice.EmployeeService.entities.Employee;
import com.employeeservice.EmployeeService.payload.EmpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpService {
    @Autowired
    private EmpClient empClient;
    List<Employee> list = List.of(new Employee(101,"Ankit", "Associate MTS"),
            new Employee(202,"Sumit", " Quality Assurance"),
            new Employee(301,"Virat", "Director"));

    public Employee getEmpById(int id){
        Employee employee = list.stream().filter(e -> e.getId() == id).findAny().orElse(null);

        // Now we need information about customer as well, So we will call the CustomerService endpoint
        // from here By Using Feign Client
        List<Customer> body = empClient.getCustomer(id).getBody();
        employee.setCustomers(body);

        return employee;
    }
}
