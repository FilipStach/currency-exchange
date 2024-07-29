package com.bank.currency_account.controller;

import com.bank.currency_account.model.Account;
import com.bank.currency_account.model.command.CreateAccountCommand;
import com.bank.currency_account.model.dto.AccountDto;
import com.bank.currency_account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    private ResponseEntity<AccountDto> addAccount(@RequestBody @Valid CreateAccountCommand command) {
        log.info("Creating account for currency" + command.currency());
        return ResponseEntity.ok(AccountDto.fromAccount(accountService.createAccount(command)));
    }
}
