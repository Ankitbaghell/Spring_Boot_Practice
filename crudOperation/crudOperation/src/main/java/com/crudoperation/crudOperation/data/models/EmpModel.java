package com.crudoperation.crudOperation.data.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Used in Service layer
public class EmpModel {
    private int eid;
    private String name;
    private String jobTitle;
    private int age;

    public EmpModel(int eid, String name, String jobTitle, int age) {
        this.eid = eid;
        this.name = name;
        this.jobTitle = jobTitle;
        this.age = age;
    }

    public EmpModel() {
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EmpModel{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
