package com.excelToJavaObject.excelToJavaObject.Repository;


import com.excelToJavaObject.excelToJavaObject.entities.Advisor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvisorRepository extends MongoRepository<Advisor, Integer> {
}
