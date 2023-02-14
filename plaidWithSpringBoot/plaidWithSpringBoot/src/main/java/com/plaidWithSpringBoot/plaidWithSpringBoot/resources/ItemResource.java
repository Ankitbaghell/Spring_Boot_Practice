package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaid.client.model.CountryCode;
import com.plaid.client.model.Institution;
import com.plaid.client.model.InstitutionsGetByIdRequest;
import com.plaid.client.model.InstitutionsGetByIdResponse;
import com.plaid.client.model.Item;
import com.plaid.client.model.ItemGetRequest;
import com.plaid.client.model.ItemGetResponse;
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
@RequestMapping("/item")
//@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

  @Autowired
  private  PlaidApi plaidClient;

//  PlaidConfig plaidConfig = new PlaidConfig();
//  private PlaidApi plaidClient = plaidConfig.getPlaidClient() ;
//
//  public ItemResource(PlaidConfig plaidConfig) {
////    this.plaidClient = plaidConfig.getPlaidClient();
//    this.plaidConfig = plaidConfig;
//  }

  @GetMapping
  public ItemResponse getItem() throws IOException {
    ItemGetRequest request = new ItemGetRequest()
      .accessToken(PlaidWithSpringBootApplication.accessToken); //------------------------------

    Response<ItemGetResponse> itemResponse = plaidClient
    .itemGet(request)
    .execute();
    
    InstitutionsGetByIdRequest institutionsRequest = new InstitutionsGetByIdRequest()
    .institutionId(itemResponse.body().getItem().getInstitutionId())
    .addCountryCodesItem(CountryCode.US);

    Response<InstitutionsGetByIdResponse> institutionsResponse = plaidClient
    .institutionsGetById(institutionsRequest)
    .execute();

    return new ItemResponse(
      itemResponse.body().getItem(), 
      institutionsResponse.body().getInstitution()
    );
  }

  public static class ItemResponse {
    @JsonProperty
    public Item item;

    @JsonProperty
    public Institution institution;

    public ItemResponse(Item item, Institution institution) {
      this.item = item;
      this.institution = institution;
    }
  }
}
