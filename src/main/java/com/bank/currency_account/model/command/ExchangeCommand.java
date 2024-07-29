package com.bank.currency_account.model.command;

import com.bank.currency_account.model.Account;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ExchangeCommand(String pesel, @NotNull Account.Currency currencyFrom, @NotNull Account.Currency currencyTo,
                              @Positive BigDecimal amount){
}
