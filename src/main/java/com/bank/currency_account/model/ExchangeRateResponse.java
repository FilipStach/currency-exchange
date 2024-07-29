package com.bank.currency_account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;


    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class Rate {
        private String no;
        private String effectiveDate;
        private double bid;
        private double ask;


        public enum RateType {
            BID {
                @Override
                public double getValue(Rate rate) {
                    return rate.getBid();
                }
            },
            ASK {
                @Override
                public double getValue(Rate rate) {
                    return 1/rate.getAsk();
                }
            };

            public abstract double getValue(Rate rate);
        }
    }
}