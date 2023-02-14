package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaid.client.model.AccountIdentity;
import com.plaid.client.model.IdentityGetRequest;
import com.plaid.client.model.IdentityGetResponse;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/identity")
//@Produces(MediaType.APPLICATION_JSON)
public class IdentityResource {

  @Autowired
  private  PlaidApi plaidClient;
//  PlaidConfig plaidConfig = new PlaidConfig();
//  private final PlaidApi plaidClient = plaidConfig.getPlaidClient();
//
//  public IdentityResource( PlaidConfig plaidConfig) {
//    this.plaidConfig = plaidConfig;
//  }

  @GetMapping
  public IdentityResponse getAccounts() throws IOException {
    IdentityGetRequest request = new IdentityGetRequest()
      .accessToken(PlaidWithSpringBootApplication.accessToken);
    Response<IdentityGetResponse> response = plaidClient 
    .identityGet(request)
    .execute();
    return new IdentityResponse(response.body());
  }

  private static class IdentityResponse {
    @JsonProperty
    private final List<AccountIdentity> identity;

    public IdentityResponse(IdentityGetResponse response) {
      this.identity = response.getAccounts();
    }
  }
}
