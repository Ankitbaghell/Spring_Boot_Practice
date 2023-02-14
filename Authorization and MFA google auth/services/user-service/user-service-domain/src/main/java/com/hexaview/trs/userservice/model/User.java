package com.hexaview.trs.userservice.model;

import com.hexaview.trs.common.util.DateUtil;
import com.hexaview.trs.common.util.UUIDUtil;
import java.time.LocalDateTime;

public class User {

  private static final String DEFAULT_USER = "System";
  private final Long id;
  private final String userUuid;

  private final String firstName;

  private final String lastName;

  private final String email;

  private final Boolean enableMFA;

  private final Boolean isActive;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
  private final String createdBy;
  private final String updatedBy;

  private final Long roleIdFk;

  private final Long validationCode;

  private final String mfaSecretKey;

  private User(Builder builder) {
    this.id = builder.id;
    this.firstName = builder.firstName;
    this.userUuid = builder.userUuid == null ? UUIDUtil.randomUUID() : builder.userUuid;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.roleIdFk = builder.roleIdFk;
    this.validationCode = builder.validationCode;
    this.mfaSecretKey = builder.mfaSecretKey;
    this.isActive = builder.isActive == null ? true : builder.isActive;
    this.enableMFA = builder.enableMFA == null ? false : builder.enableMFA;
    this.createdAt = DateUtil.getCurrentDateTime();
    this.updatedAt = DateUtil.getCurrentDateTime();
    this.createdBy = DEFAULT_USER;
    this.updatedBy = DEFAULT_USER;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getUserUuid() {
    return userUuid;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public Boolean enableMFA() {
    return enableMFA;
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

  public Long getRoleIdFk() {
    return roleIdFk;
  }

  public Long getValidationCode() {
    return validationCode;
  }

  public String getMfaSecretKey() {
    return mfaSecretKey;
  }

  public static final class Builder {
    private Long id;
    private String userUuid;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean enableMFA;

    private Boolean isActive;

    private Long roleIdFk;

    private Long validationCode;

    private String mfaSecretKey;

    public Builder(String firstName, String lastName, String email, Long roleIdFk) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.roleIdFk = roleIdFk;
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withUserUUID(String userUuid) {
      this.userUuid = userUuid;
      return this;
    }

    public Builder withIsActive(Boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    public Builder withEnableMFA(Boolean enableMFA) {
      this.enableMFA = enableMFA;
      return this;
    }

    public Builder withValidationCode(Long validationCode){
      this.validationCode = validationCode;
      return this;
    }

    public Builder withMfaSecretKey(String mfaSecretKey){
      this.mfaSecretKey = mfaSecretKey;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
