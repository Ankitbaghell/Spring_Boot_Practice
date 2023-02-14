package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.plaid.client.model.ACHClass;
import com.plaid.client.model.AccountsGetRequest;
import com.plaid.client.model.AccountsGetResponse;
import com.plaid.client.model.ItemPublicTokenExchangeRequest;
import com.plaid.client.model.ItemPublicTokenExchangeResponse;
import com.plaid.client.model.TransferAuthorizationCreateRequest;
import com.plaid.client.model.TransferAuthorizationCreateResponse;
import com.plaid.client.model.TransferCreateRequest;
import com.plaid.client.model.TransferCreateResponse;
import com.plaid.client.model.TransferNetwork;
import com.plaid.client.model.TransferType;
import com.plaid.client.model.TransferUserInRequest;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
//import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/set_access_token")
public class AccessTokenResource {
  private static final Logger LOG = LoggerFactory.getLogger(AccessTokenResource.class);
  private PlaidApi plaidClient ;

  private final List<String> plaidProducts;

  public AccessTokenResource(PlaidApi plaidClient , List<String> plaidProducts) {
    this.plaidClient = plaidClient;
    this.plaidProducts = plaidProducts;
  }

  @PostMapping
  public InfoResource.InfoResponse getAccessToken(@RequestParam("public_token") String publicToken)
    throws IOException {
      ItemPublicTokenExchangeRequest request = new ItemPublicTokenExchangeRequest()
      .publicToken(publicToken);

    Response<ItemPublicTokenExchangeResponse> response = plaidClient
      .itemPublicTokenExchange(request)
      .execute();

    // Ideally, we would store this somewhere more persistent
    PlaidWithSpringBootApplication.
      accessToken = response.body().getAccessToken();
    PlaidWithSpringBootApplication.itemID = response.body().getItemId();
    LOG.info("public token: " + publicToken);
    LOG.info("access token: " + PlaidWithSpringBootApplication.accessToken);
    LOG.info("item ID: " + response.body().getItemId());

    if (plaidProducts.contains("transfer")) {
      // We call /accounts/get to obtain first account_id - in production,
      // account_id's should be persisted in a data store and retrieved
	    // from there.
      AccountsGetRequest accountsGetRequest = new AccountsGetRequest()
        .accessToken(PlaidWithSpringBootApplication.accessToken);

      Response<AccountsGetResponse> accountsGetResponse = plaidClient
        .accountsGet(accountsGetRequest)
        .execute();

      String accountId = accountsGetResponse.body().getAccounts().get(0).getAccountId();

      TransferUserInRequest user = new TransferUserInRequest()
        .legalName("FirstName LastName");

      TransferAuthorizationCreateRequest transferAuthorizationCreateRequest = new TransferAuthorizationCreateRequest()
        .accessToken(PlaidWithSpringBootApplication.accessToken)
        .accountId(accountId)
        .type(TransferType.CREDIT)
        .network(TransferNetwork.ACH)
        .amount("1.34")
        .achClass(ACHClass.PPD)
        .user(user);

      Response<TransferAuthorizationCreateResponse> transferAuthorizationCreateResponse = plaidClient
        .transferAuthorizationCreate(transferAuthorizationCreateRequest)
        .execute();
  
      String authorizationId = transferAuthorizationCreateResponse.body().getAuthorization().getId();
      
      TransferCreateRequest transferCreateRequest = new TransferCreateRequest()
        .authorizationId(authorizationId)
        .idempotencyKey("1223abc456xyz7890001")
        .accessToken(PlaidWithSpringBootApplication.accessToken)
        .accountId(accountId)
        .type(TransferType.CREDIT)
        .network(TransferNetwork.ACH)
        .amount("1.34")
        .achClass(ACHClass.PPD)
        .description("Payment")
        .user(user);

      Response<TransferCreateResponse> transferCreateResponse = plaidClient
        .transferCreate(transferCreateRequest)
        .execute();

      PlaidWithSpringBootApplication.transferId = transferCreateResponse.body().getTransfer().getId();
    }

    return new InfoResource.InfoResponse(Arrays.asList(), PlaidWithSpringBootApplication.accessToken,
            PlaidWithSpringBootApplication.itemID);
  }

 
}
