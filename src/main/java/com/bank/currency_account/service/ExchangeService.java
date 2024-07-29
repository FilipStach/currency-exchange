package com.bank.currency_account.service;

import com.bank.currency_account.exception.CurrencyAccountNotFoundException;
import com.bank.currency_account.exception.NotEnoughMoneyException;
import com.bank.currency_account.model.Account;
import com.bank.currency_account.model.command.ExchangeCommand;
import com.bank.currency_account.model.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExchangeService {
    private final UserService userService;
    private final ExchangeRateService exchangeRateService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<AccountDto> exchange(ExchangeCommand command) {
        List<Account> accounts = userService.getUserAccountsByPesel(command.pesel());
        double exchangeRate = exchangeRateService.getBid(command);
        Account accountFrom = accounts.stream()
                .filter(p-> p.getCurrency().equals(command.currencyFrom().toString()))
                .findFirst()
                .orElseThrow(CurrencyAccountNotFoundException::new);
        Account accountTo = accounts.stream()
                .filter(p-> p.getCurrency().equals(command.currencyTo().toString()))
                .findFirst()
                .orElseThrow(CurrencyAccountNotFoundException::new);
        checkAccountBalance(command, accountFrom);
        accountFrom.setBalance(accountFrom.getBalance().subtract(command.amount()));
        accountTo.setBalance(accountTo.getBalance().add(command.amount().multiply(BigDecimal.valueOf(exchangeRate))));
        return Arrays.asList(AccountDto.fromAccount(accountFrom), AccountDto.fromAccount(accountTo));
    }

    private static void checkAccountBalance(ExchangeCommand command, Account accountFrom) {
        if(accountFrom.getBalance().compareTo(command.amount()) < 0) {
            throw new NotEnoughMoneyException();
        }
    }
}
