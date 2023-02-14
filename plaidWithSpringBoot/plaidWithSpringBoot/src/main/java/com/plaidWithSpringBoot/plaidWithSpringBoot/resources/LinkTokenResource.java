package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaid.client.model.AccountSubtype;
import com.plaid.client.model.CountryCode;
import com.plaid.client.model.DepositoryFilter;
import com.plaid.client.model.LinkTokenAccountFilters;
import com.plaid.client.model.LinkTokenCreateRequest;
import com.plaid.client.model.LinkTokenCreateRequestUser;
import com.plaid.client.model.LinkTokenCreateResponse;
import com.plaid.client.model.Products;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/create_link_token")
//@Produces(MediaType.APPLICATION_JSON)
public class LinkTokenResource {

  private final PlaidApi plaidClient ;
  private final List<String> plaidProducts;
  private final List<String> countryCodes;
  private final String redirectUri;
  private final List<Products> correctedPlaidProducts;
  private final List<CountryCode> correctedCountryCodes;

  public LinkTokenResource(PlaidApi plaidClient, List<String> plaidProducts,
    List<String> countryCodes, String redirectUri) {
    this.plaidClient = plaidClient;
    this.redirectUri = redirectUri;
    this.plaidProducts = plaidProducts;
    this.countryCodes = countryCodes;
    this.correctedPlaidProducts = new ArrayList<>();
    this.correctedCountryCodes = new ArrayList<>();
  }

  public static class LinkToken {
    @JsonProperty
    private String linkToken;


    public LinkToken(String linkToken) {
      this.linkToken = linkToken;
    }
  }

  @PostMapping
  public LinkToken getLinkToken() throws IOException {
    String clientUserId = Long.toString((new Date()).getTime());
    LinkTokenCreateRequestUser user = new LinkTokenCreateRequestUser()
		.clientUserId(clientUserId);

    for (int i = 0; i < this.plaidProducts.size(); i++){
      this.correctedPlaidProducts.add(Products.fromValue(this.plaidProducts.get(i)));
    };

    for (int i = 0; i < this.countryCodes.size(); i++){
      this.correctedCountryCodes.add(CountryCode.fromValue(this.countryCodes.get(i)));
    };

		LinkTokenCreateRequest request = new LinkTokenCreateRequest()
			.user(user)
			.clientName("Quickstart Client")
			.products(this.correctedPlaidProducts)
			.countryCodes(this.correctedCountryCodes)
			.language("en");

    	Response<LinkTokenCreateResponse> response =plaidClient
			.linkTokenCreate(request)
			.execute();
    return new LinkToken(response.body().getLinkToken());
  }
}
