package com.coderscampus.BrokerSystem;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coderscampus.BrokerSystem.domain.Account;
import com.coderscampus.BrokerSystem.domain.Authority;
import com.coderscampus.BrokerSystem.domain.User;
import com.coderscampus.BrokerSystem.repository.AccountRepository;
import com.coderscampus.BrokerSystem.repository.UserRepository;
import com.coderscampus.BrokerSystem.util.CustomPasswordEncoder;

import lombok.val;

@SpringBootApplication
public class BrokerSystemApplication {

  @Autowired
  private CustomPasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AccountRepository accountRepository;

  public static void main(String[] args) {
    SpringApplication.run(BrokerSystemApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
      System.out.println("Hello");
      // Account account = accountRepository.findByUsername("fff123").get();
      // User user = User.builder().fname("Fikre").lname("Tesfay")
      // .email("abcd@Gmail.com").gender(true).phoneNumber(1234)
      // .position("abcd").account(account).build();
      // userRepository.save(user);
      // Account account = Account.builder().username("fff123")
      // .password(passwordEncoder.getPasswordEncoder().encode("asdf"))
      // .createdDate(LocalDate.now()).isDefault(true).build();

      // Authority authority = Authority.builder().authority("ROLE_USER")
      // .account(account).build();

      // account.setAuthorities(List.of(authority));
      // System.out.println(account);
      // accountRepository.save(account);

      // user.account(account);
      // userRepository.save(user.build());
    };
  }
}
