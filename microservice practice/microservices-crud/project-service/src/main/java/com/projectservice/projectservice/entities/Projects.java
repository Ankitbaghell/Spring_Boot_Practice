package com.projectservice.projectservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;
    private String projectname;
    private int noofmembers;

    private int empId;

    public Projects(int pid, String projectname, int noofmembers) {
        this.pid = pid;
        this.projectname = projectname;
        this.noofmembers = noofmembers;
    }

    public Projects(int pid, String projectname, int noofmembers, int empId) {
        this.pid = pid;
        this.projectname = projectname;
        this.noofmembers = noofmembers;
        this.empId = empId;
    }

    public Projects() {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public int getNoofmembers() {
        return noofmembers;
    }

    public void setNoofmembers(int noofmembers) {
        this.noofmembers = noofmembers;
    }
}
