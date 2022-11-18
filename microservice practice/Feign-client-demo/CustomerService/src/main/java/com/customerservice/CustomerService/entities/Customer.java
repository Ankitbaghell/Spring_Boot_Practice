package com.customerservice.CustomerService.entities;

public class Customer {
    private int cid;
    private String name;
    private String city;

    public Customer(int cid, String name, String city, int empId) {
        this.cid = cid;
        this.name = name;
        this.city = city;
        this.empId = empId;
    }

    private int empId;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer(int cid, String name, String city) {
        this.cid = cid;
        this.name = name;
        this.city = city;
    }
}
