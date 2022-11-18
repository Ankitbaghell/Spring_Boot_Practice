package com.projectservice.projectservice.controller;

import com.projectservice.projectservice.entities.Projects;
import com.projectservice.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //get All Project
    @GetMapping("/projects")
    public List<Projects> getAllProject(){
       return projectService.getAllProject();
    }

    //get SIngle project
    @GetMapping("/project/{id}")
    public Projects getSIngleProject(@PathVariable int id){
        return projectService.getSingleProject(id);
    }

    //create project
    @PostMapping("/projects")
    public void createProject(@RequestBody Projects projects){
        projectService.createProject(projects);
    }

    //update project
    @PutMapping("/project/{id}")
    public void updateProject(@RequestBody Projects projects, @PathVariable int id){
        projectService.updateProject(projects,id);
    }

    //delete project
    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable int id){
       projectService.deleteProject(id);
    }
}
