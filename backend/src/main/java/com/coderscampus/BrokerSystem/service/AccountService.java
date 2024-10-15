package com.coderscampus.BrokerSystem.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coderscampus.BrokerSystem.domain.Account;
import com.coderscampus.BrokerSystem.domain.Authority;
import com.coderscampus.BrokerSystem.dto.AuthCredentialsRequest;
import com.coderscampus.BrokerSystem.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private AuthorityService authService;

    public Optional<Account> findUserByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    public Account saveAccount(Account account) {
        Optional<Account> accountOptional = accountRepo.findByUsername(account.getUsername());
        try {
            if (accountOptional.isPresent()) {
                // If a student with the email exists, throw an exception with a custom message
                throw new IllegalStateException("Email already taken");
            }
            return accountRepo.save(account);
        } catch (Exception e) {
            throw new IllegalStateException("Email already taken", e);
        }

    }

    public Account createAccount(AuthCredentialsRequest request) {
        Optional<Account> accountOptional = accountRepo.findByUsername(request.getUsername());
        try {
            if (accountOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            } else {
                Account newAccount = new Account();
                newAccount.setUsername(request.getUsername());
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String password = passwordEncoder.encode(request.getPassword());
                newAccount.setPassword(password);
                newAccount.setCreatedDate(LocalDate.now());
                newAccount.setIsDefault(true);
                Account savedAccount = accountRepo.save(newAccount);

                Authority authority = new Authority();
                // changed from setUser to setAccount to maintain consistency
                authority.setAccount(savedAccount);
                authority.setAuthority("ROLE_CLIENT");
                authService.saveAuthority(authority);
                return savedAccount;
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Email already taken", ex);
        }
    }
}
