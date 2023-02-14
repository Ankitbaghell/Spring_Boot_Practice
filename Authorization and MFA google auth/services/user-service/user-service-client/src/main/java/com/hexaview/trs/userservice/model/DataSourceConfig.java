package com.hexaview.trs.userservice.model;

import com.hexaview.trs.common.util.DateUtil;
import java.time.LocalDateTime;

public class DataSourceConfig {

  private static final String DEFAULT_USER = "System";

  private final Long id;

  private final String organizationDatasourceInfoUuid;

  private final String tenantId;

  private final String datasourceType;

  private final String organizationInfoFk;

  private final String driverClass;

  private final String datasourceUrl;

  private final String datasourceUserName;

  private final String datasourcePassword;

  private final String host;

  private final String port;

  private final String dbName;

  private final Boolean sslEnable;

  private final Boolean isActive;
  private final LocalDateTime createdAt;

  private final LocalDateTime updatedAt;

  private final String createdBy;

  private final String updatedBy;

  private DataSourceConfig(Builder builder) {
    this.id = builder.id;
    this.organizationDatasourceInfoUuid = builder.organizationDatasourceInfoUuid;
    this.tenantId = builder.tenantId;
    this.datasourceType = builder.datasourceType;
    this.organizationInfoFk = builder.organizationInfoFk;
    this.driverClass = builder.driverClass;
    this.datasourceUrl = builder.datasourceUrl;
    this.datasourceUserName = builder.datasourceUserName;
    this.datasourcePassword = builder.datasourcePassword;
    this.host = builder.host;
    this.port = builder.port;
    this.dbName = builder.dbName;
    this.isActive = builder.isActive;
    this.sslEnable = builder.sslEnable == null ? true : builder.sslEnable;
    this.createdAt = DateUtil.getCurrentDateTime();
    this.updatedAt = DateUtil.getCurrentDateTime();
    this.createdBy = DEFAULT_USER;
    this.updatedBy = DEFAULT_USER;
  }

  public Long getId() {
    return id;
  }

  public String getOrganizationDatasourceInfoUuid() {
    return organizationDatasourceInfoUuid;
  }

  public String getTenantId() {
    return tenantId;
  }

  public String getDatasourceType() {
    return datasourceType;
  }

  public String getOrganizationInfoFk() {
    return organizationInfoFk;
  }

  public String getDriverClass() {
    return driverClass;
  }

  public String getDatasourceUrl() {
    return datasourceUrl;
  }

  public String getDatasourceUserName() {
    return datasourceUserName;
  }

  public String getDatasourcePassword() {
    return datasourcePassword;
  }

  public String getHost() {
    return host;
  }

  public String getPort() {
    return port;
  }

  public String getDbName() {
    return dbName;
  }

  public Boolean getSslEnable() {
    return sslEnable;
  }

  public Boolean getActive() {
    return isActive;
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

  public static final class Builder {

    private Long id;

    private String organizationDatasourceInfoUuid;

    private String tenantId;

    private String datasourceType;

    private String organizationInfoFk;

    private String driverClass;

    private String datasourceUrl;

    private String datasourceUserName;

    private String datasourcePassword;

    private String host;

    private String port;

    private String dbName;

    private Boolean isActive;

    private Boolean sslEnable;

    public Builder(
        String tenantId,
        String datasourceType,
        String datasourceUserName,
        String datasourcePassword,
        String host,
        String port,
        String dbName) {
      this.tenantId = tenantId;
      this.datasourceType = datasourceType;
      this.datasourceUserName = datasourceUserName;
      this.datasourcePassword = datasourcePassword;
      this.host = host;
      this.port = port;
      this.dbName = dbName;
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withOrganizationDatasourceInfoUuid(String organizationDatasourceInfoUuid) {
      this.organizationDatasourceInfoUuid = organizationDatasourceInfoUuid;
      return this;
    }

    public Builder withOrganizationInfoFk(String organizationInfoFk) {
      this.organizationInfoFk = organizationInfoFk;
      return this;
    }

    public Builder withTenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    public Builder withDatasourceType(String datasourceType) {
      this.datasourceType = datasourceType;
      return this;
    }

    public Builder withDriverClass(String driverClass) {
      this.driverClass = driverClass;
      return this;
    }

    public Builder withDatasourceUrl(String datasourceUrl) {
      this.datasourceUrl = datasourceUrl;
      return this;
    }

    public Builder withSslEnalble(Boolean sslEnable) {
      this.sslEnable = sslEnable;
      return this;
    }

    public Builder withDatasourceUserName(String datasourceUserName) {
      this.datasourceUserName = datasourceUserName;
      return this;
    }

    public Builder withIsActive(Boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    public Builder withDatasourcePassword(String datasourcePassword) {
      this.datasourcePassword = datasourcePassword;
      return this;
    }

    public DataSourceConfig build() {
      return new DataSourceConfig(this);
    }
  }
}
