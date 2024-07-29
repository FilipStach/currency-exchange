package com.bank.currency_account.repository;

import com.bank.currency_account.model.Account;
import com.bank.currency_account.model.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("Select a from Account a left join fetch a.user u where u.pesel = :pesel")
    List<Account> getAccountsByPesel(String pesel);
}
