package com.bank.currency_account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        private BigDecimal bid;
        private BigDecimal ask;


        public enum RateType {
            BID {
                @Override
                public BigDecimal getValue(Rate rate) {
                    return rate.getBid();
                }
            },
            ASK {
                @Override
                public BigDecimal getValue(Rate rate) {
                    return BigDecimal.ONE.divide(rate.getAsk(), 4, RoundingMode.HALF_UP);
                }
            };

            public abstract BigDecimal getValue(Rate rate);
        }
    }
}