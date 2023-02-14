package com.mapstructwithbuilder.mapstructwithbuilder.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserRequest {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("is_test_user")
    private Boolean isTestUser;

    @JsonProperty("is_external_user")
    private Boolean isExternalUser;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("is_terminated")
    private Boolean isTerminated;

    @JsonProperty("designation_id")
    private Long designationId;
}
