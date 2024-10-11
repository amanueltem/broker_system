package com.coderscampus.BrokerSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.BrokerSystem.domain.User;



public interface UserRepository  extends JpaRepository<User,Long>{
   
}
