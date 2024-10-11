package com.coderscampus.BrokerSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.BrokerSystem.domain.Authority;



public interface AuthorityRepository  extends JpaRepository<Authority,Long>{
    List<Authority> findByAuthority(String authority);
}
