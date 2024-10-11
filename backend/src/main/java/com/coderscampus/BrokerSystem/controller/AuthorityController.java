package com.coderscampus.BrokerSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.BrokerSystem.domain.Account;
import com.coderscampus.BrokerSystem.enums.AuthorityEnum;
import com.coderscampus.BrokerSystem.util.AuthorityUtil;




@RestController
@RequestMapping("/api/authorities")
public class AuthorityController {
    @GetMapping
    public ResponseEntity<?> getAuthorities(@AuthenticationPrincipal Account account){
        if(AuthorityUtil.hasRole(AuthorityEnum.ROLE_ADMIN.name(), account)){
            return ResponseEntity.ok(AuthorityEnum.values());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
