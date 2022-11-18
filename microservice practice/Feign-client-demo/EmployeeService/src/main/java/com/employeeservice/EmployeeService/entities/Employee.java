package com.employeeservice.EmployeeService.entities;

import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String role;

    private List<Customer> customers;

    public int getId() {
        return id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
