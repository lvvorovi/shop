package com.shop.core.security;

import com.shop.domains.userRoles.UserRolesEntity;
import com.shop.domains.users.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetails implements UserDetails {

    UserDto userDto;

    public MyUserDetails(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRolesEntity> entity = userDto.getRoles();
        ArrayList<UserRolesEntity> userRolesEntityArrayList = new ArrayList<>(entity);
        String role = userRolesEntityArrayList.get(0).getRoles().getName();
        return List.of(new SimpleGrantedAuthority(role));
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
