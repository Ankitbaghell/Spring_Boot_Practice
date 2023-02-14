package com.excelToJavaObject.excelToJavaObject.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Advisor")
public class Advisor {
    @Id
    private int id;
    private String name;
    private List<Client> clients = new ArrayList<>();
}
