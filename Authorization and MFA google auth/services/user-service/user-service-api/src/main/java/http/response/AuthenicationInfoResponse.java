package http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuthenicationInfoResponse {

  @JsonProperty("user_uuid")
  private String userUuid;

  @JsonProperty("mfa_status")
  private Boolean mfaStatus;

  @JsonProperty("token")
  private String token;
}
