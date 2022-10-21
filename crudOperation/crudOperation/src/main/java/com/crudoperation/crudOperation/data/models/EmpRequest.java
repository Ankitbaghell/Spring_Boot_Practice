package com.crudoperation.crudOperation.data.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.validation.constraints.*;

//Used in Controller layer
public class EmpRequest {
    private int eid;

    // Hibernate Validation
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2,max = 30,message = "Name must be between 2 - 30")
    private String name;
    @NotNull(message = "Job Title cannot be empty")
//  @NotEmpty
    @Length(max = 50)
//    @Email
//    @URL
    private String jobTitle;

    @Min(value = 18, message = "Age must be greater than or equal to 18")
    @Max(value = 65,message = "Age must be less tha or equal to 65")
    private int age;

    public EmpRequest(int eid, String name, String jobTitle, int age) {
        this.eid = eid;
        this.name = name;
        this.jobTitle = jobTitle;
        this.age = age;
    }

    public EmpRequest() {
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

    @Override
    public String toString() {
        return "EmpRequest{" +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", age=" + age +
                '}';
    }
}
