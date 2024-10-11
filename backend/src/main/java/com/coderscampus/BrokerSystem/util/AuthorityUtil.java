package com.coderscampus.BrokerSystem.util;

import com.coderscampus.BrokerSystem.domain.Account;

public class AuthorityUtil {
    public static Boolean hasRole(String role, Account user) {
        return user.getAuthorities()
                .stream()
                .filter(auth -> auth.getAuthority().equals(role))
                .count() > 0;
    }
}
