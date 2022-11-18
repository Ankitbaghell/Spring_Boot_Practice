package com.customerservice.CustomerService.service;

import com.customerservice.CustomerService.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    List<Customer> list = List.of(new Customer(1,"Adobe", "Pune", 101),
            new Customer(2,"Google", "Silicon valley", 101),
            new Customer(3,"Amazon", "Noida", 202));

    public List<Customer> getCustomerByEmpId(int id){
        List<Customer> customers = list.stream().filter(c -> c.getEmpId() == id).collect(Collectors.toList());
        return customers;
    }
}
