package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.plaid.client.model.ItemPublicTokenCreateRequest;
import com.plaid.client.model.ItemPublicTokenCreateResponse;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;

@RestController
@RequestMapping("/create_public_token")

public class PublicTokenResource {

  @Autowired
  private  PlaidApi plaidClient;
//  private final PlaidApi plaidClient ;
//
//  public PublicTokenResource(PlaidApi plaidClient) {
//     this.plaidClient = plaidClient;
//  }

  @GetMapping
  public ItemPublicTokenCreateResponse createPublicToken() throws IOException {

    ItemPublicTokenCreateRequest request = new ItemPublicTokenCreateRequest() 
    .accessToken(PlaidWithSpringBootApplication.accessToken);

    Response<ItemPublicTokenCreateResponse> response = plaidClient
      .itemCreatePublicToken(request)
      .execute();

    return response.body();
  }
}
