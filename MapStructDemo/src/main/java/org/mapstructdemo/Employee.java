package org.mapstructdemo;

import java.util.Date;

public class Employee {
    private Integer empId;
    private String name;
    private String city;

    private String about;

    //map a bean with references to other beans.
    private Manager manager;

    // mapping with implicit type conversions
    private Date startDate;

    public Employee() {
    }

    public Employee(String name, String city, String about, Manager manager, Date startDate) {
        this.name = name;
        this.city = city;
        this.about = about;
        this.manager = manager;
        this.startDate = startDate;
    }

    public Employee(Integer empId, String name, String city, String about, Manager manager, Date startDate) {
        this.empId = empId;
        this.name = name;
        this.city = city;
        this.about = about;
        this.manager = manager;
        this.startDate = startDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", about='" + about + '\'' +
                ", manager=" + manager +
                ", startDate=" + startDate +
                '}';
    }
}
