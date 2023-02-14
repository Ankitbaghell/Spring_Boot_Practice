package com.plaidWithSpringBoot.plaidWithSpringBoot.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plaid.client.model.RemovedTransaction;
import com.plaid.client.model.Transaction;
import com.plaid.client.model.TransactionsSyncRequest;
import com.plaid.client.model.TransactionsSyncResponse;
import com.plaid.client.request.PlaidApi;
import com.plaidWithSpringBoot.plaidWithSpringBoot.PlaidWithSpringBootApplication;
import com.plaidWithSpringBoot.plaidWithSpringBoot.config.PlaidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/transactions")
//@Produces(MediaType.APPLICATION_JSON)
public class TransactionsResource {

  @Autowired
  private  PlaidApi plaidClient;
//  @Autowired
//  PlaidConfig plaidConfig = new PlaidConfig();
//  private final PlaidApi plaidClient = plaidConfig.getPlaidClient();
//
//
//  public TransactionsResource(PlaidConfig plaidConfig) {
//    this.plaidConfig = plaidConfig;
//  }

  @GetMapping
  public TransactionsResponse getTransactions() throws IOException {
    // Set cursor to empty to receive all historical updates
    String cursor = null;

    // New transaction updates since "cursor"
    List<Transaction> added = new ArrayList<Transaction>();
    List<Transaction> modified = new ArrayList<Transaction>();
    List<RemovedTransaction> removed = new ArrayList<RemovedTransaction>();
    boolean hasMore = true;
    // Iterate through each page of new transaction updates for item
    while (hasMore) {
      TransactionsSyncRequest request = new TransactionsSyncRequest()
        .accessToken(PlaidWithSpringBootApplication.accessToken)
        .cursor(cursor);

      Response<TransactionsSyncResponse> response = plaidClient.transactionsSync(request).execute();
      TransactionsSyncResponse responseBody = response.body();

      // Add this page of results
      added.addAll(responseBody.getAdded());
      modified.addAll(responseBody.getModified());
      removed.addAll(responseBody.getRemoved());
      hasMore = responseBody.getHasMore();
      // Update cursor to the next cursor
      cursor = responseBody.getNextCursor();
    }

    // Return the 8 most recent transactions
    added.sort(new CompareTransactionDate());
    List<Transaction> latestTransactions = added.subList(Math.max(added.size() - 8, 0), added.size());
    return new TransactionsResponse(latestTransactions);
  }

  private class CompareTransactionDate implements Comparator<Transaction> {
    @Override
    public int compare(Transaction o1, Transaction o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
  }
  
  private static class TransactionsResponse {
    @JsonProperty
    private final List<Transaction> latest_transactions;
  
    public TransactionsResponse(List<Transaction> latestTransactions) {
      this.latest_transactions = latestTransactions;
    }
  }
}
