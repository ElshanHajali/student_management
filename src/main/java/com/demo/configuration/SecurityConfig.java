package com.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.demo.security.Permissions.*;
import static com.demo.security.Roles.*;
import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authz -> authz
                        .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
//                        .antMatchers("/api/**").hasRole(STUDENT.name())
                        .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                        .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                        .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                        .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(ADMIN.name(), ADMINTRANIEE.name())
                        .anyRequest()
                        .authenticated())
                .csrf().disable().httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService detailsService() {
        UserDetails tom = User.builder()
                .username("Tom")
                .password(passwordEncoder.encode("tpassword"))
                .authorities(STUDENT.grantedAuthorities())
                .build();

        UserDetails lora = User.builder()
                .username("Lora")
                .password(passwordEncoder.encode("lopassword"))
                .authorities(ADMIN.grantedAuthorities())
                .build();

        UserDetails linda = User.builder()
                .username("Linda")
                .password(passwordEncoder.encode("lipassword"))
                .authorities(ADMINTRANIEE.grantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(tom, lora, linda);
    }
}














