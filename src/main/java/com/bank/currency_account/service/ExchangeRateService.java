package com.bank.currency_account.service;

import com.bank.currency_account.client.ExchangeRateClient;
import com.bank.currency_account.exception.WrongCurrencyException;
import com.bank.currency_account.model.Account;
import com.bank.currency_account.model.ExchangeRateResponse;
import com.bank.currency_account.model.command.ExchangeCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateService {
    private final ExchangeRateClient exchangeRateClient;

    @Cacheable(cacheNames = "exchangeRates", key = "#command.currencyTo")
    public double getBid(ExchangeCommand command) {
        ExchangeRateResponse.Rate.RateType rateType;
        String currency;
        if(command.currencyFrom() == Account.Currency.PLN){
            rateType = ExchangeRateResponse.Rate.RateType.ASK;
            currency = command.currencyTo().toString();
        }
        else if (command.currencyTo() == Account.Currency.PLN) {
            rateType = ExchangeRateResponse.Rate.RateType.BID;
            currency = command.currencyFrom().toString();
        }
        else {
            throw new WrongCurrencyException();
        }
        ExchangeRateResponse response = exchangeRateClient.getExchangeRate(currency);
        List<ExchangeRateResponse.Rate> rates = response.getRates();
        if (!rates.isEmpty()) {
            return rateType.getValue(rates.get(0));
        }
        throw new RuntimeException("Failed to fetch exchange rate");
    }
}