package com.jpaMapping.jpaMapping.repository;

import com.jpaMapping.jpaMapping.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RdbmsRepository extends JpaRepository<Student, Integer> {


}
