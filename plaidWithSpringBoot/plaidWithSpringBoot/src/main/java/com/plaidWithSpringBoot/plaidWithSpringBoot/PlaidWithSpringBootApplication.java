package com.plaidWithSpringBoot.plaidWithSpringBoot;

import com.plaid.client.ApiClient;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.AccessTokenResource;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.AccountsResource;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.AuthResource;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.BalanceResource;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.InfoResource;
//import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.InvestmentTransactionsResource;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.LinkTokenResource;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.LinkTokenWithPaymentResource;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.PublicTokenResource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableWebMvc
@SpringBootApplication
public class PlaidWithSpringBootApplication {
	public static String accessToken;
	public static String itemID;
	public static String paymentId;
	public static String transferId;

	private PlaidApi plaidClient;
	private ApiClient apiClient;
	public String plaidEnv;

	public static void main(final String[] args) {
		SpringApplication.run(PlaidWithSpringBootApplication.class, args);
	}

	@Value("${plaid.env}")
	public void setPlaidEnv(String plaidEnv) {
		switch (plaidEnv) {
			case "sandbox":
				this.plaidEnv = ApiClient.Sandbox;
				break;
			default:
				this.plaidEnv = ApiClient.Sandbox;
		}
	}

	@Value("${plaid.products}")
	private String plaidProducts;

	@Value("${plaid.countryCodes}")
	private String plaidCountryCodes;

	@Value("${plaid.clientID}")
	private String plaidClientId;

	@Value("${plaid.secret}")
	private String plaidSecret;

	@Value("${plaid.redirectUri}")
	private String redirectUri;

	@PostConstruct
	public void init() {
		List<String> products = Arrays.asList(plaidProducts.split(","));
		List<String> countryCodes = Arrays.asList(plaidCountryCodes.split(","));

		System.out.println(plaidClientId);
		System.out.println(plaidSecret);

		Map<String, String> apiKeys = new HashMap<>();
		apiKeys.put("clientId", plaidClientId);
		apiKeys.put("secret", plaidSecret);
		apiKeys.put("plaidVersion", "2020-09-14");

		apiClient = new ApiClient(apiKeys);
		apiClient.setPlaidAdapter(plaidEnv);

		plaidClient = apiClient.createService(PlaidApi.class);
	}

//	@Bean
//	public InfoResource infoResource() {
//		return new InfoResource(Arrays.asList(plaidProducts.split(",")));
//	}

//	@Bean
//	public InvestmentTransactionsResource investmentTransactionsResource() {
//		return new InvestmentTransactionsResource(plaidClient);
//	}

	@Bean
	public LinkTokenResource linkTokenResource() {
		return new LinkTokenResource(plaidClient, Arrays.asList(plaidProducts.split(",")),
				Arrays.asList(plaidCountryCodes.split(",")), redirectUri);
	}

	@Bean
	public AccessTokenResource accessTokenResource() {
		return new AccessTokenResource(plaidClient, Arrays.asList(plaidProducts.split(",")));
	}

	@Bean
	public AccountsResource accountsResource() {
		return new AccountsResource(plaidClient);
	}

	@Bean
	public AuthResource authResource() {
		return new AuthResource(plaidClient);
	}

//	@Bean
//	public PublicTokenResource publicTokenResource() {
//		return  new PublicTokenResource(plaidClient);
//	}

	@Bean
	public BalanceResource balanceResource() {
		return new BalanceResource(plaidClient);}

	@Bean
	public LinkTokenWithPaymentResource linkTokenWithPaymentResource() {
		return new LinkTokenWithPaymentResource(plaidClient, Arrays.asList(plaidProducts.split(",")),
				Arrays.asList(plaidCountryCodes.split(",")), redirectUri);
	}

	@Bean
	protected PlaidApi client() {
		return plaidClient;
	}

	protected ApiClient apiClient() {
		return apiClient;
	}
}



