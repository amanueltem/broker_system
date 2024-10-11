package com.coderscampus.BrokerSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.BrokerSystem.domain.Authority;
import com.coderscampus.BrokerSystem.repository.AuthorityRepository;



@Service
public class AuthorityService {
   @Autowired
   private AuthorityRepository authRepo;
   public Authority saveAuthority(Authority auth){
    return authRepo.save(auth);
   } 
}
