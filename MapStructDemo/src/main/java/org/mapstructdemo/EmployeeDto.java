package org.mapstructdemo;

import java.util.Date;

public class EmployeeDto {
    private Integer empId;
    private String name;
    private String city;

    private String description;

    //map a bean with references to other beans.
    private ManagerDto managerDto;

    // mapping with implicit type conversions
    private String joiningDate;

    public EmployeeDto() {
    }

    public EmployeeDto( String name, String city, String description, ManagerDto managerDto, String joiningDate) {

        this.name = name;
        this.city = city;
        this.description = description;
        this.managerDto = managerDto;
        this.joiningDate = joiningDate;
    }
    public EmployeeDto(Integer empId, String name, String city, String description, ManagerDto managerDto, String joiningDate) {
        this.empId = empId;
        this.name = name;
        this.city = city;
        this.description = description;
        this.managerDto = managerDto;
        this.joiningDate = joiningDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ManagerDto getManagerDto() {
        return managerDto;
    }

    public void setManagerDto(ManagerDto managerDto) {
        this.managerDto = managerDto;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", managerDto=" + managerDto +
                ", joiningDate='" + joiningDate + '\'' +
                '}';
    }
}
