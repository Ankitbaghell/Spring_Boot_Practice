package constant;

public final class UserServiceConstants {

  public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";

  public static final String BASE_URL = "/users";

  public static final String GET_URL = "/get";

  public static final String GET_BY_ID_URL = GET_URL + "/{id}";

  public static final String GET_BY_UUID_URL = GET_URL + "/uuid/{id}";
  public static final String GET_DATASOURCE_CONFIG = "/organization/get/datasource";

  public static final String LOGIN_URL = "/login";

  public static final String VALIDATE_TOKEN_URL = "/authenticate-token";
  public static final String GENERATE_MFA = "/generate/{username}";

  public static final String VALIDATE_MFA = "/validate/key";
}
