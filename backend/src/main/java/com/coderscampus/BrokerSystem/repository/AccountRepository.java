package com.coderscampus.BrokerSystem.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.BrokerSystem.domain.Account;





public interface AccountRepository extends JpaRepository<Account,Long> {
    public Optional<Account> findByUsername(String usernmae);
   
}
