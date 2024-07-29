package com.bank.currency_account.controller;

import com.bank.currency_account.model.command.ExchangeCommand;
import com.bank.currency_account.model.dto.AccountDto;
import com.bank.currency_account.service.ExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<List<AccountDto>> exchange(@RequestBody @Valid ExchangeCommand command) {
        log.info("Exchanging for user: " + command.pesel());
        return ResponseEntity.ok(exchangeService.exchange(command));
    }
}
