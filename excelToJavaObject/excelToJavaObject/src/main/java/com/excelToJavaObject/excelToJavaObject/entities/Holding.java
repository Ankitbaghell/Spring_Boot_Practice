package com.excelToJavaObject.excelToJavaObject.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Holding {
    private int quantity;
    private String securitySymbol;
    private String securityTypeCode;
    private String securityName;
    private double marketValue;
    private double MVAL;
}
