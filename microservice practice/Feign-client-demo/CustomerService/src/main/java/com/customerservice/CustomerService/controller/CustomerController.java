package com.customerservice.CustomerService.controller;

import com.customerservice.CustomerService.entities.Customer;
import com.customerservice.CustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Customer>> getCustomerOfEmpId(@PathVariable int id){
        List<Customer> customers = customerService.getCustomerByEmpId(id);
        return new ResponseEntity<>(customers, HttpStatus.ACCEPTED);
    }
}
