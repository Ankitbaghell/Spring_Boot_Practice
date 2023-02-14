package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.plaid.client.model.TransferGetRequest;
import com.plaid.client.model.TransferGetResponse;
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
@RequestMapping("/transfer")
//@Produces(MediaType.APPLICATION_JSON)
public class TransferResource {

  @Autowired
  private  PlaidApi plaidClient;

//  @Autowired
//  PlaidConfig plaidConfig = new PlaidConfig();
//  private final PlaidApi plaidClient = plaidConfig.getPlaidClient();
//
//  public TransferResource(PlaidConfig plaidConfig) {
//    this.plaidConfig = plaidConfig;
//  }

  @GetMapping
  public TransferGetResponse getTransfer() throws IOException {
    
    TransferGetRequest request = new TransferGetRequest()
      .transferId(PlaidWithSpringBootApplication.transferId);
    Response<TransferGetResponse> response = plaidClient 
      .transferGet(request)
      .execute();
    return response.body();
  }
}
