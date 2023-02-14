package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaid.client.model.InvestmentsHoldingsGetRequest;
import com.plaid.client.model.InvestmentsHoldingsGetResponse;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;

@RestController
@RequestMapping("/holdings")
//@Produces(MediaType.APPLICATION_JSON)
public class HoldingsResource {

  @Autowired
   PlaidApi plaidClient;

//  public HoldingsResource( PlaidApi plaidClient) {
//    this.plaidClient = plaidClient;
//  }

  @GetMapping
  public HoldingsResponse getAccounts() throws IOException {

    InvestmentsHoldingsGetRequest request = new InvestmentsHoldingsGetRequest()
    .accessToken(PlaidWithSpringBootApplication.accessToken);

    Response<InvestmentsHoldingsGetResponse> response = plaidClient
      .investmentsHoldingsGet(request)
      .execute();

    return new HoldingsResponse(response.body());
  }

  private static class HoldingsResponse {
    @JsonProperty
    private final InvestmentsHoldingsGetResponse holdings;

    public HoldingsResponse(InvestmentsHoldingsGetResponse response) {
      this.holdings = response;
    }
  }
}
