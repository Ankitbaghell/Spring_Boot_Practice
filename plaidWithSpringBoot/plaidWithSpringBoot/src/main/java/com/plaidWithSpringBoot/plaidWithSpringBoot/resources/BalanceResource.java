package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.plaid.client.model.AccountsBalanceGetRequest;
import com.plaid.client.model.AccountsGetResponse;
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
@RequestMapping("/balance")
public class BalanceResource {


  private PlaidApi plaidClient ;

  public BalanceResource(PlaidApi plaidClient) {
    this.plaidClient =  plaidClient;
  }

  @GetMapping
  public AccountsGetResponse getAccounts() throws IOException {
    AccountsBalanceGetRequest request = new AccountsBalanceGetRequest()
      .accessToken(PlaidWithSpringBootApplication.accessToken);

    Response<AccountsGetResponse> response = plaidClient
      .accountsBalanceGet(request)
      .execute();
    return response.body();
  }
}
