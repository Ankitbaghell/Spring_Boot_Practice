package com.hexaview.trs.userservice.model;

public final class UserCredential {

  private final Long id;

  private final String userCredentailUuid;

  private final Long userId;

  private final String password;

  private UserCredential(Builder builder) {
    this.id = builder.id;
    this.userId = builder.userId;
    this.password = builder.password;
    this.userCredentailUuid = builder.userCredentialUuid;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }

  public String getUserCredentailUuid() {
    return userCredentailUuid;
  }

  public static final class Builder {

    private Long id;

    private Long userId;

    private String userCredentialUuid;

    private String password;

    public Builder(Long userId) {
      this.userId = userId;
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withPassword(String password) {
      this.password = password;
      return this;
    }

    public Builder withUserCredentialUUID(String userCredentialUuid) {
      this.userCredentialUuid = userCredentialUuid;
      return this;
    }

    public UserCredential build() {
      return new UserCredential(this);
    }
  }
}
