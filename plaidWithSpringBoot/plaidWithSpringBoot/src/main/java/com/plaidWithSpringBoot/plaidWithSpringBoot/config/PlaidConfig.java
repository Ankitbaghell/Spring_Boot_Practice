package com.plaidWithSpringBoot.plaidWithSpringBoot.config;

import com.plaid.client.ApiClient;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.resources.LinkTokenResource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Configuration
//@Component
public class PlaidConfig {


    private PlaidApi plaidClient = null ;


    private ApiClient apiClient = null;


    @Value("${plaid.env}")
    private String plaidEnvValue;

    @Value("${plaid.products}")
    private String plaidProductsValue;

    @Value("${plaid.countryCodes}")
    private String plaidCountryCodesValue;

    @Value("${plaid.clientID}")
    private String plaidClientId;

    @Value("${plaid.secret}")
    private String plaidSecret;

    @Value("${plaid.redirectUri}")
    private String plaidRedirectUri;

//    @PostConstruct
    public void init() {
        System.out.println("_____******POST CONSTRUCT BHAI");
        String plaidEnv;
        switch(plaidEnvValue) {
            case "sandbox":
                plaidEnv = ApiClient.Sandbox;
                break;
            case "development":
                plaidEnv = ApiClient.Development;
                break;
            case "production":
                plaidEnv = ApiClient.Production;
                break;
            default:
                plaidEnv = ApiClient.Sandbox;
        }

        List<String> plaidProducts = Arrays.asList(plaidProductsValue.split(","));
        List<String> countryCodes = Arrays.asList(plaidCountryCodesValue.split(","));

//        if (configuration.getPlaidRedirectUri() != null && configuration.getPlaidRedirectUri().length() > 0) {
//            redirectUri = configuration.getPlaidRedirectUri();
//        }

        System.out.println(plaidClientId);
        System.out.println(plaidSecret);

        HashMap<String, String> apiKeys = new HashMap<>();
        apiKeys.put("clientId", plaidClientId);
        apiKeys.put("secret", plaidSecret);
        apiKeys.put("plaidVersion", "2020-09-14");

        apiClient = new ApiClient(apiKeys);
        apiClient.setPlaidAdapter(plaidEnv);

        plaidClient = apiClient.createService(PlaidApi.class);
    }

//    @Bean
    public PlaidApi getPlaidClient() {
        System.out.println("_____******PLAID CLIENT BHAAAAAAAA_____________");
        return plaidClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }
}
