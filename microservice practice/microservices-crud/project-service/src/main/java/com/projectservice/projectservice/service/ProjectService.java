package com.projectservice.projectservice.service;

import com.projectservice.projectservice.entities.Projects;
import com.projectservice.projectservice.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    //get All project
    public List<Projects> getAllProject(){
        return projectRepo.findAll();
    }

    //get Single project of emp
    public Projects getSingleProject(int id){
        return projectRepo.findById(id).get();
    }

    //create projectr
    public void createProject(Projects projects){
        projectRepo.save(projects);
    }

    //delete project
    public void deleteProject(int id){
        projectRepo.deleteById(id);
    }

    //update project
    public void updateProject(Projects projects, int id){
        projects.setPid(id);
        projectRepo.save(projects);
    }
}
