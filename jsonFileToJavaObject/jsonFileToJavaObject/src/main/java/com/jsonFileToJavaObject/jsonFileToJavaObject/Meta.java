package com.jsonFileToJavaObject.jsonFileToJavaObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Meta {
        @JsonProperty("TRACE")
        private String trace;
}
