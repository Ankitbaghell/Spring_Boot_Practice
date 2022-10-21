package org.mapstructdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-14T14:06:05+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
public class EmpMapStructImpl implements EmpMapStruct {

    @Override
    public EmployeeDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setDescription( employee.getAbout() );
        employeeDto.setManagerDto( managerToDto( employee.getManager() ) );
        if ( employee.getStartDate() != null ) {
            employeeDto.setJoiningDate( new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" ).format( employee.getStartDate() ) );
        }
        employeeDto.setEmpId( employee.getEmpId() );
        employeeDto.setName( employee.getName() );
        employeeDto.setCity( employee.getCity() );

        return employeeDto;
    }

    @Override
    public Employee fromDto(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setAbout( employeeDto.getDescription() );
        employee.setManager( dtoToManager( employeeDto.getManagerDto() ) );
        try {
            if ( employeeDto.getJoiningDate() != null ) {
                employee.setStartDate( new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" ).parse( employeeDto.getJoiningDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        employee.setEmpId( employeeDto.getEmpId() );
        employee.setName( employeeDto.getName() );
        employee.setCity( employeeDto.getCity() );

        return employee;
    }

    @Override
    public ManagerDto managerToDto(Manager manager) {
        if ( manager == null ) {
            return null;
        }

        ManagerDto managerDto = new ManagerDto();

        managerDto.setManagerId( manager.getManagerId() );
        managerDto.setManagerName( manager.getManagerName() );

        return managerDto;
    }

    @Override
    public Manager dtoToManager(ManagerDto managerDto) {
        if ( managerDto == null ) {
            return null;
        }

        Manager manager = new Manager();

        manager.setManagerId( managerDto.getManagerId() );
        manager.setManagerName( managerDto.getManagerName() );

        return manager;
    }
}
