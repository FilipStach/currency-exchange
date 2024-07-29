package com.bank.currency_account.client;

import com.bank.currency_account.model.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchangeRateClient", url = "http://api.nbp.pl/api/exchangerates/rates/c/")
public interface ExchangeRateClient {
    @GetMapping("{currency}/today/")
    ExchangeRateResponse getExchangeRate(@PathVariable("currency") String currency);
}
