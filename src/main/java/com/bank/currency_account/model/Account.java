package com.bank.currency_account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_pesel")
    private User user;
    private BigDecimal balance;
    private String currency;

    public Account(User user, BigDecimal balance, Currency currency) {
        this.user = user;
        this.balance = balance;
        this.currency = currency.toString();
    }

    public enum Currency {
        PLN, USD
    }
}
