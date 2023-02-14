package http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GetDataSourceConfigResponse {

  @JsonProperty("driver_class")
  private String driverClass;

  @JsonProperty("url")
  private String url;

  @JsonProperty("user_name")
  private String email;

  @JsonProperty("host")
  private String host;

  @JsonProperty("port")
  private String port;

  @JsonProperty("db_name")
  private String dbName;

  @JsonProperty("tenant_id")
  private String tenantId;

  @JsonProperty("password")
  private String password;
}
