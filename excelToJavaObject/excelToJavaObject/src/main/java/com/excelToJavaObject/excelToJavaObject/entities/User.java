package com.excelToJavaObject.excelToJavaObject.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
}
