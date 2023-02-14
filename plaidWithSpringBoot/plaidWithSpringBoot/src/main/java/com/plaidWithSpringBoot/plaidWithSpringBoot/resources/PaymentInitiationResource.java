package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.plaid.client.model.PaymentInitiationPaymentGetRequest;
import com.plaid.client.model.PaymentInitiationPaymentGetResponse;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;

// This functionality is only relevant for the UK Payment Initiation product.
@RestController
@RequestMapping("/payment")
//@Produces(MediaType.APPLICATION_JSON)
public class PaymentInitiationResource {
  private static final Logger LOG = LoggerFactory.getLogger(PaymentInitiationResource.class);

  @Autowired
  private  PlaidApi plaidClient;
//  PlaidConfig plaidConfig = new PlaidConfig();
//
//  private final PlaidApi plaidClient = plaidConfig.getPlaidClient();
//
//  public PaymentInitiationResource(PlaidConfig plaidConfig) {
//    this.plaidConfig = plaidConfig;
//  }

  @GetMapping
  public PaymentResponse getPayment() throws IOException {
    String paymentId = PlaidWithSpringBootApplication.paymentId;

    PaymentInitiationPaymentGetRequest request = new PaymentInitiationPaymentGetRequest()
      .paymentId(paymentId);

    Response<PaymentInitiationPaymentGetResponse> response =
      plaidClient
      .paymentInitiationPaymentGet(request)
      .execute();
    if (!response.isSuccessful()) {
      try {
        Gson gson = new Gson();
        Error errorResponse = gson.fromJson(response.errorBody().string(), Error.class);
        LOG.error("error: " + errorResponse);
      } catch (Exception e) {
          LOG.error("error", e);
      }
    }
    return new PaymentResponse(response.body());
  }

  private static class PaymentResponse {
    @JsonProperty
    private final PaymentInitiationPaymentGetResponse payment;

    public PaymentResponse(PaymentInitiationPaymentGetResponse response) {
      this.payment = response;
    }
  }
}
