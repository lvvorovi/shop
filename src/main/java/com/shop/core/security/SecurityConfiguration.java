package com.shop.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    MyUserDetailService myUserDetailService;

    public SecurityConfiguration(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/products").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/products").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")

                .antMatchers(HttpMethod.PUT, "/users").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.GET, "/items").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/items").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/items").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/items").hasAnyRole("USER", "ADMIN")

                .and().formLogin();


    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
