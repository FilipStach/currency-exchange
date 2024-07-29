package com.bank.currency_account.service.providers;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateProvider {
    public LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }
}
