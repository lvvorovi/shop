package com.shop.core.security;

import com.shop.core.security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyUserDetailService myUserDetailService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration(MyUserDetailService myUserDetailService, JwtRequestFilter jwtRequestFilter) {
        this.myUserDetailService = myUserDetailService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.authorizeRequests()
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

                .and().formLogin();*/
        http.csrf().disable().authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() throws NoSuchAlgorithmException {
        return new BCryptPasswordEncoder(12);
    }

}
