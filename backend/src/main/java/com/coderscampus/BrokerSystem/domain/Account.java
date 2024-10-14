package com.coderscampus.BrokerSystem.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdDate;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();
    private Boolean isDefault;

    // // geters
    // public Long getId() {
    // return id;
    // }

    // public LocalDate getCreatedDate() {
    // return createdDate;
    // }

    // @Override
    // public String getUsername() {
    // return username;
    // }

    // @Override
    // public String getPassword() {
    // return password;
    // }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // return authorities;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    // return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isEnabled() {
    // return true;
    // }

    // // seters
    // public Boolean getIsDefault() {
    // return isDefault;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    // public void setCreatedDate(LocalDate createdDate) {
    // this.createdDate = createdDate;
    // }

    // public void setUsername(String username) {
    // this.username = username;
    // }

    // public void setPassword(String password) {
    // this.password = password;
    // }

    // public void setAuthorities(List<Authority> authorities) {
    // this.authorities = authorities;
    // }

    // public void setIsDefault(Boolean isDefault) {
    // this.isDefault = isDefault;
    // }

}
