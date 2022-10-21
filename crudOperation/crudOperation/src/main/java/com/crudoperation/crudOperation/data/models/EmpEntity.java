package com.crudoperation.crudOperation.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Used in Repository layer
@Entity
public class EmpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;
    private String name;
    private String jobTitle;
    private int age;

    public EmpEntity() {
    }

    @Override
    public String toString() {
        return "EmpEntity{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", age=" + age +
                '}';
    }

    public EmpEntity(int eid, String name, String jobTitle, int age) {
        this.eid = eid;
        this.name = name;
        this.jobTitle = jobTitle;
        this.age = age;
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
