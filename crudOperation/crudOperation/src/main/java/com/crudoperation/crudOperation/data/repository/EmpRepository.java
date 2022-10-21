package com.crudoperation.crudOperation.data.repository;


import com.crudoperation.crudOperation.data.models.EmpEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends CrudRepository<EmpEntity,Integer> {

}
