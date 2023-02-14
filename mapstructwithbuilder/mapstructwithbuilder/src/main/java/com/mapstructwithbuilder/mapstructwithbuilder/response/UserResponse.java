package com.mapstructwithbuilder.mapstructwithbuilder.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("user_uuid")
    private String userUuid;

    @JsonProperty("is_test_user")
    private Boolean isTestUser;

    @JsonProperty("is_external_user")
    private Boolean isExternalUser;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("is_terminated")
    private Boolean isTerminated;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("update_at")
    private LocalDateTime updatedAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("update_by")
    private String updatedBy;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("system")
    private String system;

    @JsonProperty("designation_name")
    private String designationName;
}

