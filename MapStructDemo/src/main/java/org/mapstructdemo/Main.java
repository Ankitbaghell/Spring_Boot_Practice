package org.mapstructdemo;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        EmpMapStruct empMapStruct = new EmpMapStructImpl();

        Manager manager = new Manager(501,"Saurabh");
        Date date = new Date();
        Employee employee1 = new Employee(101,"Ankit","Bhopal","Java Developer",manager,date);
        EmployeeDto employeeDto = empMapStruct.toDto(employee1);
        System.out.println(employeeDto);

        ManagerDto managerDto = new ManagerDto(703,"Rahul");
        String dateString = "06-03-2001 07:56:32";
        EmployeeDto employeeDto1 = new EmployeeDto(202,"Sachin","Mumbai","Data Science",managerDto,dateString);
        Employee employee = empMapStruct.fromDto(employeeDto1);
        System.out.println(employee);
    }
}