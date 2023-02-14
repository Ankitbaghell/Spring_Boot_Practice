package com.mapstructwithbuilder.mapstructwithbuilder.model;
import com.mapstructwithbuilder.mapstructwithbuilder.Default;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private static final String DEFAULT_SYSTEM = "Internal";
    private static final String DEFAULT_USER = "System";
    private static final Integer DEFAULT_VERSION = 1;

    private final Long id;
    private final String username;
    private final String userUuid;
    private final Boolean isTestUser;
    private final Boolean isExternalUser;
    private final Boolean isActive;
    private final Boolean isTerminated;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String createdBy;
    private final String updatedBy;
    private final Integer version;
    private final String system;

    private final Boolean mfaEnabled;

    private User(User.Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.userUuid = builder.userUuid == null ? UUID.randomUUID().toString() : builder.userUuid;
        this.isTestUser = builder.isTestUser;
        this.isExternalUser = builder.isExternalUser;
        this.isActive = builder.isActive;
        this.isTerminated = builder.isTerminated;
        this.createdAt = builder.createdAt== null ? LocalDateTime.now() : builder.createdAt;
        this.updatedAt = builder.updatedAt == null ? LocalDateTime.now() : builder.updatedAt;;
        this.createdBy = builder.createdBy == null ? DEFAULT_USER : builder.createdBy;
        this.updatedBy = builder.updatedBy  == null ? DEFAULT_USER : builder.updatedBy;
        this.version = builder.version;
        this.system = builder.system;
        this.mfaEnabled = builder.mfaEnabled;
    }

//    --------------
//    public static User.Builder builder() {
//        return new User();
//    }
//    ------------

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public Boolean isTestUser() {
        return isTestUser;
    }

    public Boolean isExternalUser() {
        return isExternalUser;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Boolean isTerminated() {
        return isTerminated;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public String getSystem() {
        return system;
    }

    public Boolean getMfaEnabled() {
        return mfaEnabled;
    }

    public static final class Builder {
        private Long id;
        private String username;
        private String userUuid;
        private Boolean isTestUser;
        private Boolean isExternalUser;
        private Boolean isActive;
        private Boolean isTerminated;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String createdBy;
        private String updatedBy;
        private Integer version;
        private String system;
        private Boolean mfaEnabled;

        public Builder(){
        }

        @Default
        public Builder(
                String username,
                String userUuid,
                Boolean isTestUser,
                Boolean isExternalUser,
                Boolean isActive,
                Boolean isTerminated,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                String createdBy,
                String updatedBy,
                Integer version,
                String system) {
            this.username = username;
            this.userUuid = userUuid;
            this.isTestUser = isTestUser;
            this.isExternalUser = isExternalUser;
            this.isActive = isActive;
            this.isTerminated = isTerminated;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.createdBy = createdBy;
            this.updatedBy = updatedBy;
            this.version = DEFAULT_VERSION;
            this.system = DEFAULT_SYSTEM;
        }


        public Builder(
                String username,
                Boolean isTestUser,
                Boolean isExternalUser,
                Boolean isActive,
                Boolean isTerminated) {
            this.username = username;
            this.userUuid = UUID.randomUUID().toString();
            this.isTestUser = isTestUser;
            this.isExternalUser = isExternalUser;
            this.isActive = isActive;
            this.isTerminated = isTerminated;
            this.createdBy = DEFAULT_USER;
            this.updatedBy = DEFAULT_USER;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
            this.version = DEFAULT_VERSION;
            this.system = DEFAULT_SYSTEM;
        }

        public Builder(String username) {
            this.username = username;
            this.userUuid = UUID.randomUUID().toString();
            this.isTestUser = false;
            this.isExternalUser = false;
            this.isActive = true;
            this.isTerminated = false;
            this.createdBy = DEFAULT_USER;
            this.updatedBy = DEFAULT_USER;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
            this.version = DEFAULT_VERSION;
            this.system = DEFAULT_SYSTEM;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withMfaEnabled(Boolean mfaEnabled) {
            this.mfaEnabled = mfaEnabled;
            return this;
        }

        public Builder withIsTestUser(Boolean isTestUser) {
            this.isTestUser = isTestUser;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

