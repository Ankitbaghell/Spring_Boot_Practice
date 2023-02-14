package com.jpaMapping.jpaMapping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Project {

    @Id
    private int project_id;

    private String project_name;

    @ManyToMany(mappedBy = "projects")
//    @JoinTable(name = "projects_and_students")
    private List<Student> students = new ArrayList<>();
}
