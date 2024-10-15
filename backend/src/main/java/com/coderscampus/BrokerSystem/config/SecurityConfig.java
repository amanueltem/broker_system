package com.coderscampus.BrokerSystem.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.coderscampus.BrokerSystem.filter.JwtFilter;
import com.coderscampus.BrokerSystem.util.CustomPasswordEncoder;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    @Autowired
    private JwtFilter jwtFilter;

    // @Override
    // @Bean
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    // return super.authenticationManagerBean();
    // }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // @Override
    // public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder.getPasswordEncoder());
    // }
    @Bean
    public AuthenticationProvider authenticator() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(new BCryptPasswordEncoder());

        return dao;
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http = http.csrf().disable().cors().disable();
    // http = http
    // .sessionManagement()
    // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // .and();
    // http = http.exceptionHandling()
    // .authenticationEntryPoint((request, response, ex) -> {
    // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    // }).and();

    // http.authorizeRequests()
    // .antMatchers("/api/auth/**", "/images/**").permitAll()
    // .anyRequest().authenticated();
    // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http = http.csrf(customizer -> customizer.disable())
                .cors(customizer -> customizer.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(customizer -> {
                    customizer.authenticationEntryPoint((request, response, ex) -> {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                    });
                })
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers("/api/auth/**", "/images/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
