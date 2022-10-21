package com.crudoperation.crudOperation.services;

import com.crudoperation.crudOperation.data.mapper.EmpMapper;
import com.crudoperation.crudOperation.data.models.EmpEntity;
import com.crudoperation.crudOperation.data.models.EmpModel;
import com.crudoperation.crudOperation.data.models.EmpRequest;
import com.crudoperation.crudOperation.data.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CrudService {
    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private EmpMapper empMapper;

    //get single
    public EmpRequest getEmp(int id){
        EmpEntity empEntity = empRepository.findById(id).get();
        EmpModel empModel = empMapper.modelFromEntity(empEntity);
        return empMapper.requestFromModel(empModel);
    }

    //get All
    public List<EmpRequest> getAllEmp(){
        List<EmpEntity> all = (List<EmpEntity>) empRepository.findAll();
        List<EmpModel> empModels = empMapper.entityListToModelList(all);
        return empMapper.modelListToRequestList(empModels);
    }

    //post
    public EmpRequest createEmp(EmpModel empModel){
        EmpEntity empEntity = empMapper.modelToEntity(empModel);
        EmpEntity save = empRepository.save(empEntity);
        return empMapper.requestFromModel(empMapper.modelFromEntity(save));
    }

    //put
    public EmpRequest updateEmp(EmpModel empModel, int id){
        empModel.setEid(id);
        EmpEntity empEntity = empMapper.modelToEntity(empModel);
        EmpEntity save = empRepository.save(empEntity);
        return empMapper.requestFromModel(empMapper.modelFromEntity(save));
    }

    //delete
    public void deleteEmp(int id){
        empRepository.deleteById(id);
    }
}
