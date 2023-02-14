package http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ValidateCodeResponse {

  @JsonProperty("token")
  private String token;

  @JsonProperty("is_valid_code")
  private Boolean isValidCode;
}
