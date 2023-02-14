package com.hexaview.trs.userservice.model;

public class ValidateToken {
  private final String status;
  private final String message;
  private final User user;

  private final String role;

  private ValidateToken(Builder builder) {
    this.status = builder.status;
    this.message = builder.message;
    this.user = builder.user;
    this.role = builder.role;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public User getUser() {
    return user;
  }

  public String getRole() {
    return role;
  }

  public static final class Builder {
    private String status;
    private String message;
    private User user;
    private String role;

    public Builder(String status, String message) {
      this.status = status;
      this.message = message;
    }

    public Builder withUser(User user) {
      this.user = user;
      return this;
    }

    public Builder withRole(String role) {
      this.role = role;
      return this;
    }

    public ValidateToken build() {
      return new ValidateToken(this);
    }
  }
}
