package com.projectservice.projectservice.repository;

import com.projectservice.projectservice.entities.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Projects, Integer> {

}
