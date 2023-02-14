package com.hexaview.trs.userservice.model;

import com.hexaview.trs.common.util.DateUtil;
import com.hexaview.trs.common.util.UUIDUtil;
import java.time.LocalDateTime;

public class Token {

  private static final String DEFAULT_USER = "System";

  private final Long id;

  private final String tokenUuid;
  private final String token;
  private final Long userId;
  private final String type;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
  private final String createdBy;
  private final String updatedBy;

  private final Boolean isActive;

  private Token(Builder builder) {
    this.id = builder.id;
    this.tokenUuid = builder.tokenUuid == null ? UUIDUtil.randomUUID() : builder.tokenUuid;
    this.token = builder.token;
    this.userId = builder.userId;
    this.type = builder.type;
    this.createdAt = DateUtil.getCurrentDateTime();
    this.updatedAt = DateUtil.getCurrentDateTime();
    this.createdBy = DEFAULT_USER;
    this.updatedBy = DEFAULT_USER;
    this.isActive = builder.isActive;
  }

  public Long getId() {
    return id;
  }

  public String getToken() {
    return token;
  }

  public Long getUserId() {
    return userId;
  }

  public String getType() {
    return type;
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

  public Boolean getActive() {
    return isActive;
  }

  public String getTokenUuid() {
    return tokenUuid;
  }

  public static final class Builder {
    private Long id;

    private String tokenUuid;
    private String token;
    private Long userId;
    private String type;

    private Boolean isActive;

    public Builder(String token, Long userId, String type) {
      this.token = token;
      this.userId = userId;
      this.type = type;
    }

    public Builder withIsActive(Boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withTokenUUID(String tokenUuid) {
      this.tokenUuid = tokenUuid;
      return this;
    }

    public Token build() {
      return new Token(this);
    }
  }
}
