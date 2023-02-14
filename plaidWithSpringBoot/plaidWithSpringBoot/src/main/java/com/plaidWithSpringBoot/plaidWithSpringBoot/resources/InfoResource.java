package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
//@Produces(MediaType.APPLICATION_JSON)
public class InfoResource {
  private final List<String> plaidProducts;

  public InfoResource(List<String> plaidProducts) {
    this.plaidProducts = plaidProducts;
  }

  public static class InfoResponse {
    @JsonProperty
    private final String itemId;
    @JsonProperty
    private final String accessToken;
    @JsonProperty
    private final List<String> products;

    public InfoResponse(List<String> plaidProducts, String accessToken, String itemID) {
      this.products = plaidProducts;
      this.accessToken = accessToken;
      this.itemId = itemID;
    }
  }

  @PostMapping
  public InfoResponse getInfo() {
    return new InfoResponse(plaidProducts, PlaidWithSpringBootApplication.accessToken,
            PlaidWithSpringBootApplication.itemID);
  }
}
