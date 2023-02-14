package com.hexaview.trs.userservice.model;

import com.hexaview.trs.common.util.DateUtil;
import com.hexaview.trs.common.util.UUIDUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TrsRole {

    private static final String DEFAULT_SYSTEM = "Internal";
    private static final String DEFAULT_USER = "System";
    private static final Integer DEFAULT_VERSION = 1;

    private final Long id;

    private final String roleUuid;
    private final String roleName;
    private final String description;
    private final Integer version;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String createdBy;
    private final String updatedBy;
    private final String system;

    private TrsRole(Builder builder) {
        this.id = builder.id;
        this.roleName = builder.roleName;
        this.description = builder.description;
        this.version = builder.version;
        this.roleUuid = builder.roleUuid==null ? UUIDUtil.randomUUID() : builder.roleUuid;
        this.system = DEFAULT_SYSTEM;
        this.createdAt = DateUtil.getCurrentDateTime();
        this.updatedAt = DateUtil.getCurrentDateTime();
        this.createdBy = DEFAULT_USER;
        this.updatedBy = DEFAULT_USER;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getVersion() {
        return version;
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

    public String getSystem() {
        return system;
    }

    public String getRoleUuid() {
        return roleUuid;
    }

    public static final class Builder {
        private Long id;

        private String roleUuid;
        private String roleName;
        private String description;
        private Integer version;

        public Builder(String roleName, String description) {
            this.roleName = roleName;
            this.description = description;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUuid(String roleUuid) {
            this.roleUuid = roleUuid;
            return this;
        }

        public Builder withVersion(Integer version) {
            this.version = version;
            return this;
        }

        public TrsRole build() {
            return new TrsRole(this);
        }
    }
}
