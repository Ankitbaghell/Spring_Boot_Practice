package com.crudoperation.crudOperation.data.mapper;

import com.crudoperation.crudOperation.data.entities.Employee;
import com.crudoperation.crudOperation.data.models.EmpEntity;
import com.crudoperation.crudOperation.data.models.EmpModel;
import com.crudoperation.crudOperation.data.models.EmpRequest;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpMapper {
    EmpRequest empToRequest(Employee employee);
    Employee empFromRequest(EmpRequest empRequest);

    EmpModel requestToModel(EmpRequest empRequest);
    EmpRequest requestFromModel(EmpModel empModel);

    EmpEntity modelToEntity(EmpModel empModel);
    EmpModel modelFromEntity(EmpEntity empEntity);

    List<EmpModel> entityListToModelList(List<EmpEntity> empEntityList);
    List<EmpRequest> modelListToRequestList(List<EmpModel> empModelList);
    List<Employee> requestListToEmpList(List<EmpRequest> empRequestList);
}
