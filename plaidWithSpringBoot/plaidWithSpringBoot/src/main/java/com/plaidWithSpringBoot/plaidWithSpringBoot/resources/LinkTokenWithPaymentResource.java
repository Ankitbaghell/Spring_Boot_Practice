package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.plaid.client.model.CountryCode;
import com.plaid.client.model.LinkTokenCreateRequest;
import com.plaid.client.model.LinkTokenCreateRequestPaymentInitiation;
import com.plaid.client.model.LinkTokenCreateRequestUser;
import com.plaid.client.model.LinkTokenCreateResponse;
import com.plaid.client.model.PaymentAmount;
import com.plaid.client.model.PaymentInitiationAddress;
import com.plaid.client.model.PaymentInitiationPaymentCreateRequest;
import com.plaid.client.model.PaymentInitiationPaymentCreateResponse;
import com.plaid.client.model.PaymentInitiationRecipientCreateRequest;
import com.plaid.client.model.PaymentInitiationRecipientCreateResponse;
import com.plaid.client.model.Products;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/create_link_token_for_payment")
//@Produces(MediaType.APPLICATION_JSON)
public class LinkTokenWithPaymentResource {

  private PlaidApi plaidClient ;
  private final String redirectUri;

  public LinkTokenWithPaymentResource(PlaidApi plaidClient, List<String> plaidProducts,
    List<String> countryCodes, String redirectUri) {
    this.plaidClient = plaidClient;
    this.redirectUri = redirectUri;
  }

  @PostMapping
  public LinkTokenResource.LinkToken getLinkToken() throws IOException {

    PaymentInitiationAddress address = new PaymentInitiationAddress()
    .street(Arrays.asList("Street Name 999"))
    .city("City")
    .postalCode("99999")
    .country("GB");

    PaymentInitiationRecipientCreateRequest recipientCreateRequest = new PaymentInitiationRecipientCreateRequest()
      .name("Jonathan Doe")
      .iban("GB33BUKB20201555555555")
      .address(address);


    Response<PaymentInitiationRecipientCreateResponse> recipientResponse =
      this.plaidClient
      .paymentInitiationRecipientCreate(recipientCreateRequest)
      .execute();


    String recipientId = recipientResponse.body().getRecipientId();

    PaymentAmount amount = new PaymentAmount()
    .currency(PaymentAmount.CurrencyEnum.GBP)
    .value(999.99);

    PaymentInitiationPaymentCreateRequest paymentCreateRequest = new PaymentInitiationPaymentCreateRequest()
      .recipientId(recipientId)
      .reference("reference")
      .amount(amount);

    Response<PaymentInitiationPaymentCreateResponse> paymentResponse = plaidClient
      .paymentInitiationPaymentCreate(paymentCreateRequest)
      .execute();


    String paymentId = paymentResponse.body().getPaymentId();
      PlaidWithSpringBootApplication.paymentId = paymentId;

    LinkTokenCreateRequestPaymentInitiation paymentInitiation = new LinkTokenCreateRequestPaymentInitiation()
      .paymentId(paymentId);

    // This should correspond to a unique id for the current user.
    // Typically, this will be a user ID number from your application.
    // Personally identifiable information, such as an email address or phone number, should not be used here.
    String clientUserId = Long.toString((new Date()).getTime());
    LinkTokenCreateRequestUser user = new LinkTokenCreateRequestUser()
		.clientUserId(clientUserId);

		LinkTokenCreateRequest request = new LinkTokenCreateRequest()
			.user(user)
			.clientName("Quickstart Client")
      // The 'payment_initiation' product has to be the only element in the 'products' list.
			.products(Arrays.asList(Products.PAYMENT_INITIATION))
      // Institutions from all listed countries will be shown.
			.countryCodes(Arrays.asList(CountryCode.GB))
			.language("en")
      .redirectUri(this.redirectUri)
      .paymentInitiation(paymentInitiation);

    	Response<LinkTokenCreateResponse> response =plaidClient
			.linkTokenCreate(request)
			.execute();

    return new LinkTokenResource.LinkToken(response.body().getLinkToken());
  }
}
