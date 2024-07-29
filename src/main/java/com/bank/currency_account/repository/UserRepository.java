package com.bank.currency_account.repository;

import com.bank.currency_account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("Select u from User u left join fetch u.accounts a")
    List<User> findAllUsers();

    @Query("Select u from User u left join fetch u.accounts a where u.pesel = :pesel")
    Optional<User> findUserByPesel(String pesel);
}
