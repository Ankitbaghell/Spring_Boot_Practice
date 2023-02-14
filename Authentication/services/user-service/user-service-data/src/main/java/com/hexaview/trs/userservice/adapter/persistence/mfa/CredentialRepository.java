package com.hexaview.trs.userservice.adapter.persistence.mfa;

import com.warrenstrange.googleauth.ICredentialRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CredentialRepository implements ICredentialRepository {

  private final Map<String, UserTOTP> usersKeys =
      new HashMap<String, UserTOTP>() {
        {
          put("kek@kek.com", null);
          put("alice@gmail.com", null);
          put("123-456", null);
        }
      };

  @Override
  public String getSecretKey(String userName) {
    return usersKeys.get(userName).getSecretKey();
  }

  @Override
  public void saveUserCredentials(
      String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
    usersKeys.put(userName, new UserTOTP(userName, secretKey, validationCode, scratchCodes));
  }

  public UserTOTP getUser(String username) {

    return usersKeys.get(username);
  }

  class UserTOTP {
    private String username;
    private String secretKey;
    private int validationCode;
    private List<Integer> scratchCodes;

    public UserTOTP() {}

    public UserTOTP(
        String username, String secretKey, int validationCode, List<Integer> scratchCodes) {
      this.username = username;
      this.secretKey = secretKey;
      this.validationCode = validationCode;
      this.scratchCodes = scratchCodes;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getSecretKey() {
      return secretKey;
    }

    public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
    }

    public int getValidationCode() {
      return validationCode;
    }

    public void setValidationCode(int validationCode) {
      this.validationCode = validationCode;
    }

    public List<Integer> getScratchCodes() {
      return scratchCodes;
    }

    public void setScratchCodes(List<Integer> scratchCodes) {
      this.scratchCodes = scratchCodes;
    }
  }
}
