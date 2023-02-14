package com.hexaview.trs.userservice.model;

public class AuthenticationInfo {
  private String userUuid;
  private Boolean mfaStatus;
  private Token token;

  private AuthenticationInfo(Builder builder) {

    this.userUuid = builder.userUuid;
    this.mfaStatus = builder.mfaStatus;
    this.token = builder.token;
  }

  public String getUserUuid() {
    return userUuid;
  }

  public Boolean getMfaStatus() {
    return mfaStatus;
  }

  public Token getToken() {
    return token;
  }

  public static final class Builder {

    private String userUuid;
    private Boolean mfaStatus;
    private Token token;

    public Builder(String userUuid, Boolean mfaStatus) {
      this.userUuid = userUuid;
      this.mfaStatus = mfaStatus;
    }

    public Builder withToken(Token token) {
      this.token = token;
      return this;
    }

    public AuthenticationInfo build() {
      return new AuthenticationInfo(this);
    }
  }
}
