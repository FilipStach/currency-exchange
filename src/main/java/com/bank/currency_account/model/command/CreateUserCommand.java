package com.bank.currency_account.model.command;

import com.bank.currency_account.validation.Pesel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record CreateUserCommand(@NotEmpty @Pattern(regexp = "^[a-zA-Z]*$") String name, @Pattern(regexp = "^[a-zA-Z]*$") @NotEmpty String surname, @Pesel String pesel, @Min(0L) BigDecimal balance) {
}
