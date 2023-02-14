package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.plaid.client.model.AuthGetRequest;
import com.plaid.client.model.AuthGetResponse;
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
@RequestMapping("/auth")
public class AuthResource {

   PlaidApi plaidClient;

  public AuthResource(PlaidApi plaidClient) {
    this.plaidClient = plaidClient;
  }

  @GetMapping
  public AuthGetResponse getAccounts() throws IOException {

//    System.out.println();

    AuthGetRequest request = new AuthGetRequest()
    .accessToken(PlaidWithSpringBootApplication.accessToken);
    Response<AuthGetResponse> response = plaidClient
      .authGet(request)
      .execute();

    return response.body();
  }
}
