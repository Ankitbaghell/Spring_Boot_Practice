package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaid.client.model.InvestmentsTransactionsGetRequest;
import com.plaid.client.model.InvestmentsTransactionsGetRequestOptions;
import com.plaid.client.model.InvestmentsTransactionsGetResponse;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/investments_transactions")
//@Produces(MediaType.APPLICATION_JSON)
public class InvestmentTransactionsResource {

    @Autowired
    private  PlaidApi plaidClient;
//  PlaidConfig plaidConfig = new PlaidConfig();
//  private final PlaidApi plaidClient = plaidConfig.getPlaidClient();
//
//  public InvestmentTransactionsResource(PlaidConfig plaidConfig) {
//    this.plaidConfig = plaidConfig;
//  }

  @GetMapping
  public InvestmentTransactionsResponse getAccounts() throws IOException {
    LocalDate startDate = LocalDate.now().minusDays(30);
    LocalDate endDate = LocalDate.now();
    InvestmentsTransactionsGetRequestOptions options = new InvestmentsTransactionsGetRequestOptions()
    .count(100);

    InvestmentsTransactionsGetRequest request = new InvestmentsTransactionsGetRequest()
      .accessToken(PlaidWithSpringBootApplication.accessToken)
      .startDate(startDate)
      .endDate(endDate)
      .options(options);

    Response<InvestmentsTransactionsGetResponse> response = plaidClient
      .investmentsTransactionsGet(request)
      .execute();
    return new InvestmentTransactionsResponse(response.body());
  }

  private static class InvestmentTransactionsResponse {
    @JsonProperty
    private InvestmentsTransactionsGetResponse investmentsTransactions;

    public InvestmentTransactionsResponse(InvestmentsTransactionsGetResponse investmentTransactions) {
      this.investmentsTransactions = investmentTransactions;
    }
  }
}
