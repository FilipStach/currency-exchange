package com.bank.currency_account.service;

import com.bank.currency_account.exception.UserNotFoundException;
import com.bank.currency_account.model.Account;
import com.bank.currency_account.model.User;
import com.bank.currency_account.model.command.CreateUserCommand;
import com.bank.currency_account.model.dto.UserDto;
import com.bank.currency_account.repository.AccountRepository;
import com.bank.currency_account.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @PostConstruct
    private void init(){
        User user = new User("98112303425", "Michal", "Stach");
        Account account = new Account(user, BigDecimal.valueOf(2020L), Account.Currency.PLN);
        user.addAccount(account);

        userRepository.save(user);
        accountRepository.save(account);

        User user1 = new User("00292503452", "Filip", "Stach");
        Account account1 = new Account(user1, BigDecimal.valueOf(2020L), Account.Currency.PLN);
        user.addAccount(account1);

        Account account2 = new Account(user1, BigDecimal.valueOf(0L), Account.Currency.USD);
        user.addAccount(account2);

        userRepository.save(user1);
        accountRepository.save(account1);
        accountRepository.save(account2);
    }


    @Transactional
    public UserDto addUser(CreateUserCommand command) {
        User user = new User(command.pesel(), command.name(), command.surname());
        Account account = new Account(user, command.balance(), Account.Currency.PLN);
        user.addAccount(account);
        user = userRepository.save(user);
        accountRepository.save(account);
        return UserDto.fromUser(user);
    }
    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        return userRepository.findAllUsers().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public User getUserByPesel(String pesel) {
        return userRepository.findUserByPesel(pesel).orElseThrow(UserNotFoundException::new);
    }
    @Transactional(readOnly = true)
    public List<Account> getUserAccountsByPesel(String pesel) {
        return accountRepository.getAccountsByPesel(pesel);
    }
}
