package com.shop.core.security;

import com.shop.domains.users.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    UserDto userDto;

    public MyUserDetails(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userDto.getRoles().toString()));
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDto.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDto.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDto.getCredentialsNotExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDto.getEnabled();
    }
}
