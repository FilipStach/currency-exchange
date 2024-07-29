package com.bank.currency_account.model.dto;


import com.bank.currency_account.model.Account;

import java.math.BigDecimal;

public record AccountDto (String userName, String userSurname, Account.Currency currency, BigDecimal balance){
    public static AccountDto fromAccount(Account account) {
        return new AccountDto(account.getUser().getName(), account.getUser().getSurname(), Account.Currency.valueOf(account.getCurrency()), account.getBalance());
    }
}
