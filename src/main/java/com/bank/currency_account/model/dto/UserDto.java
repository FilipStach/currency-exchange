package com.bank.currency_account.model.dto;

import com.bank.currency_account.model.User;

public record UserDto (String name, String surname, String pesel, Integer numberOfAccounts){

    public static UserDto fromUser(User user) {
        return new UserDto(user.getName(), user.getSurname(), user.getPesel(), user.getAccounts().size());
    }
}
