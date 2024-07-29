package com.bank.currency_account.controller;

import com.bank.currency_account.model.command.CreateUserCommand;
import com.bank.currency_account.model.dto.AccountDto;
import com.bank.currency_account.model.dto.UserDto;
import com.bank.currency_account.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody CreateUserCommand command) {
        log.info("Adding user");
        return ResponseEntity.ok(userService.addUser(command));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        log.info("Getting users");
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{pesel}")
    public ResponseEntity<UserDto> getUser(@PathVariable String pesel) {
        log.info("Getting user by pesel: " + pesel);
        return ResponseEntity.ok(UserDto.fromUser(userService.getUserByPesel(pesel)));
    }

    @GetMapping("/{pesel}/accounts")
    public ResponseEntity<List<AccountDto>> getAccountsByPesel (@PathVariable String pesel) {
        log.info("Getting accounts by pesel: " + pesel);
        return ResponseEntity.ok(userService.getUserAccountsByPesel(pesel).stream().map(AccountDto::fromAccount).collect(Collectors.toList()));
    }
}
