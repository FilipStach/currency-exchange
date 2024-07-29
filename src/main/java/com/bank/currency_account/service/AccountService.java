package com.bank.currency_account.service;

import com.bank.currency_account.exception.AccountAlreadyCreatedException;
import com.bank.currency_account.model.Account;
import com.bank.currency_account.model.User;
import com.bank.currency_account.model.command.CreateAccountCommand;
import com.bank.currency_account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserService userService;
    private final AccountRepository accountRepository;

    @Transactional
    public Account createAccount(CreateAccountCommand command) {
        User user = userService.getUserByPesel(command.pesel());
        List<Account> userAccounts = userService.getUserAccountsByPesel(command.pesel());
        userAccounts.forEach(account -> {
            if(account.getCurrency().equals(command.currency().toString())) {
                throw new AccountAlreadyCreatedException();
            }
        });
        Account account = new Account(user, BigDecimal.ZERO, command.currency());
        return accountRepository.save(account);
    }
}
