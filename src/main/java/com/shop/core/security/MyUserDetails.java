package com.shop.core.security;

import com.shop.domains.userRoles.UserRolesEntity;
import com.shop.domains.users.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Boolean isEnabled;
    private final Boolean isCredentialsNotExpired;
    private final Boolean isAccountNonLocked;
    private final Boolean isAccountNonExpired;
    private final Collection<GrantedAuthority> authorities;

    public MyUserDetails(UserDto userDto) {
        this.username = userDto.getFirstName();
        this.password = userDto.getPassword();
        this.isEnabled = userDto.getEnabled();
        this.isCredentialsNotExpired = userDto.getCredentialsNotExpired();
        this.isAccountNonLocked = userDto.getAccountNonLocked();
        this.isAccountNonExpired = userDto.getAccountNonExpired();

        List<UserRolesEntity> entityList = new ArrayList<>(userDto.getRoles());
        List<String> stringList = new ArrayList<>();
        entityList.forEach(entity -> stringList.add(entity.getRole().getName()));
        this.authorities = userDto.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
