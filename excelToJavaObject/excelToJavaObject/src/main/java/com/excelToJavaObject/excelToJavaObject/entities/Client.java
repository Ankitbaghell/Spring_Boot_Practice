package com.excelToJavaObject.excelToJavaObject.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Client {
    @Id
    private int clientAccountID;
    private String clientAccountName;
    private List<Holding> holdings = new ArrayList<>();
}
