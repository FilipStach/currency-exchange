package com.bank.currency_account.validation;

import com.bank.currency_account.service.providers.DateProvider;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    private final DateProvider dateProvider;

    @Autowired
    public PeselValidator(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {
        if (pesel.length() != 11) {
            return false;
        }

        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += Integer.parseInt(String.valueOf(pesel.charAt(i))) * weights[i];
        }

        int checksum = (10 - (sum % 10)) % 10;

        if (checksum != Integer.parseInt(String.valueOf(pesel.charAt(10)))) {
            return false;
        }

        // existing age validation code
        int year;
        int month;
        int day;
        if((int) pesel.charAt(2) > 1){
            year = 2000 + Integer.parseInt(pesel.substring(0,2));
            month = Integer.parseInt(pesel.substring(2,4)) - 20;
        }
        else {
            year = 1900 + Integer.parseInt(pesel.substring(0, 1));
            month = Integer.parseInt(pesel.substring(2, 4));
        }
        day = Integer.parseInt(pesel.substring(4, 6));
        LocalDateTime currentDateTime = dateProvider.getCurrentDateTime();
        return !currentDateTime.toLocalDate().minusYears(18).isBefore(LocalDate.of(year,month,day));
    }
}