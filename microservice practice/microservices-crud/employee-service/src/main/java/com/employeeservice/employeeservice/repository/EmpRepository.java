package com.employeeservice.employeeservice.repository;

import com.employee_service.Tables;
import com.employee_service.tables.pojos.Employee;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpRepository {
    @Autowired
    private DSLContext dslContext;

    //get all emp
    public List<Employee> getAllEmp(){
        List<Employee> employees = dslContext.selectFrom(Tables.EMPLOYEE).fetchInto(Employee.class);
        return employees;
    }
    //get single emp
    public Employee getSingleEmp(int id){
        List<Employee> employees = dslContext.selectFrom(Tables.EMPLOYEE).where(Tables.EMPLOYEE.EMPID.eq(id)).fetchInto(Employee.class);
        return employees.get(0);
    }
    //create a emp
    public void createEmp(Employee employee){
        dslContext.insertInto(Tables.EMPLOYEE, Tables.EMPLOYEE.EMPID,
                Tables.EMPLOYEE.EMPNAME, Tables.EMPLOYEE.CITY, Tables.EMPLOYEE.P_ID)
                .values(employee.getEmpid(), employee.getEmpname(), employee.getCity(), employee.getPId())
                .execute();
    }

    //update a emp
    public void updateEmp(Employee employee, int id){
        dslContext.update(Tables.EMPLOYEE)
                .set(Tables.EMPLOYEE.EMPID,id)
                .set(Tables.EMPLOYEE.EMPNAME, employee.getEmpname())
                .set(Tables.EMPLOYEE.CITY, employee.getCity())
                .set(Tables.EMPLOYEE.P_ID, employee.getPId())
                .execute();
    }
    //delete a emp
    public void deleteEmp(int id){
        dslContext.deleteFrom(Tables.EMPLOYEE).where(Tables.EMPLOYEE.EMPID.eq(id)).execute();
    }
}
