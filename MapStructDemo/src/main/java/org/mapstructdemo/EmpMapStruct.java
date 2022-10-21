package org.mapstructdemo;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmpMapStruct {
        //Implementation class of this interface is automatically generated
        // by mapstruct in target folder
        @Mapping(source = "about", target = "description")   //when name of data member different
        @Mapping(source = "manager",target = "managerDto")
        @Mapping(source = "startDate",target = "joiningDate", dateFormat = "dd-MM-yyyy HH:mm:ss") //convert date into string
        //If the id field of the source entity is null, we want to generate a random id and assign it
        @Mapping(source = "empId", target = "empId",defaultExpression = "java(java.util.UUID.randomUUID().toString())")
        EmployeeDto toDto(Employee employee);   //convert employee object to employeedto

        @InheritInverseConfiguration(name="toDto")    // to apply reverse mapping of toDto method
        Employee fromDto(EmployeeDto employeeDto);  //convert employeeDto object to employee


        // we need to add a method to convert the Manager to ManagerDto and vice versa
        // if MapStruct detects that the object type needs to be converted and the method
        // to convert exists in the same class, it will use it automatically.
        ManagerDto managerToDto(Manager manager);
        Manager dtoToManager(ManagerDto managerDto);
}
