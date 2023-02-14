package response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserResponse {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("user_name")
  private String username;

  @JsonProperty("is_active")
  private boolean isActive;
}
