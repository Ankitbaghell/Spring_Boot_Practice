package com.employeeservice.employeeservice.model;

import com.employee_service.tables.pojos.Projects;

public class EmployeeDto {
    private  int empid;
    private String empName;
    private String city;

    private Projects projects;

    public EmployeeDto(int empid, String empName, String city, Projects projects) {
        this.empid = empid;
        this.empName = empName;
        this.city = city;
        this.projects = projects;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public EmployeeDto() {
    }
}
