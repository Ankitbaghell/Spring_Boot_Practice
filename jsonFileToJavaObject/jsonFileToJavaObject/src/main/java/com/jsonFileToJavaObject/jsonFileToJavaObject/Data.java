package com.jsonFileToJavaObject.jsonFileToJavaObject;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@lombok.Data
public class Data {
    private String date;
    private List<Account> accounts;
    private List<Security> securities;
    private Source source;

    @lombok.Data
    public static class Account {
        private List<Position> positions;
        private List<Transaction> transactions;
        @JsonProperty("account_id")
        private String accountId;
        @JsonProperty("account_credential")
        private String accountCredential;

        private Map<String, Object> dynamicProperties = new HashMap<>();

        @JsonAnySetter
        public void setDynamicProperty(String name, Object value) {
            dynamicProperties.put(name, value);
        }

        @lombok.Data
        public static class Position {
            @JsonProperty("position_timeseries_id")
            private PositionTimeseriesId positionTimeseriesId;
            @JsonProperty("currency_code")
            private String currencyCode;
            private String date;
            @JsonProperty("security_id")
            private String securityId;
            private double units;
            private double value;
            @JsonProperty("tax_lots")
            private List<TaxLot> taxLots;
            @JsonProperty("meta")
            private Meta meta;
            private Map<String, Object> dynamicProperties = new HashMap<>();

            @JsonAnySetter
            public void setDynamicProperty(String name, Object value) {
                dynamicProperties.put(name, value);
            }

            @lombok.Data
            public static class PositionTimeseriesId {
                private String custodian;
                private String account;
                @JsonProperty("security_id")
                private String securityId;

                private Map<String, Object> dynamicProperties = new HashMap<>();

                @JsonAnySetter
                public void setDynamicProperty(String name, Object value) {
                    dynamicProperties.put(name, value);
                }

            }

            @lombok.Data
            public static class TaxLot {
                @JsonProperty("adjusted_cost_basis")
                private double adjustedCostBasis;
                @JsonProperty("cost_basis_currency_code")
                private String costBasisCurrencyCode;
                @JsonProperty("open_date")
                private String openDate;
                @JsonProperty("original_cost_basis")
                private double originalCostBasis;
                private double units;
                @JsonProperty("meta")
                private Meta meta;

                private Map<String, Object> dynamicProperties = new HashMap<>();
                @JsonAnySetter
                public void setDynamicProperty(String name, Object value) {
                    dynamicProperties.put(name, value);
                }
            }
        }

        @lombok.Data
        public static class Transaction {
            private String type;
            private double units;
            @JsonProperty("security_id")
            private String securityId;
            @JsonProperty("trade_date")
            private String tradeDate;
            @JsonProperty("posted_date")
            private String postedDate;
            @JsonProperty("settle_date")
            private String settleDate;
            @JsonProperty("cash_account")
            private String cashAccount;
            private String comment;
            private double fees;
            @JsonProperty("transaction_id")
            private String transactionId;
            @JsonProperty("currency_code")
            private String currencyCode;
            @JsonProperty("accrued_income")
            private double accruedIncome;
            @JsonProperty("meta")
            private Meta meta;

            private Map<String, Object> dynamicProperties = new HashMap<>();

            @JsonAnySetter
            public void setDynamicProperty(String name, Object value) {
                dynamicProperties.put(name, value);
            }


        }
    }

    @lombok.Data
    public static class Security {
        @JsonProperty("security_id")
        private String securityId;
        @JsonProperty("vendor_id")
        private String vendorId;
        private String name;
        private String symbol;
        private String cusip;
        private String isin;
        private String sedol;
        @JsonProperty("currency_code")
        private String currencyCode;
        private String type;
        @JsonProperty("meta")
        private Meta meta;

        private Map<String, Object> dynamicProperties = new HashMap<>();

        @JsonAnySetter
        public void setDynamicProperty(String name, Object value) {
            dynamicProperties.put(name, value);
        }

    }

    @lombok.Data
    public static class Source {
        private String details;
        private String type;
        @JsonProperty("source_version")
        private String sourceVersion;
        @JsonProperty("creation_date")
        private String creationDate;
        private Map<String, Object> dynamicProperties = new HashMap<>();

        @JsonAnySetter
        public void setDynamicProperty(String name, Object value) {
            dynamicProperties.put(name, value);
        }
    }
}
