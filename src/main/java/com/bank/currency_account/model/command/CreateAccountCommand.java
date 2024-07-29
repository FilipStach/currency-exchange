package com.bank.currency_account.model.command;

import com.bank.currency_account.model.Account;
import jakarta.validation.constraints.NotNull;

public record CreateAccountCommand(String pesel, @NotNull Account.Currency currency) {
}
