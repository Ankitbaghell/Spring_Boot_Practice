package http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValidateCodeRequest {

  @JsonProperty("username")
  private String userName;

  @JsonProperty("code")
  private Integer code;

  @JsonProperty("user_uuid")
  private String userUuid;
}
