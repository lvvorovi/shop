package com.shop.core.security;

import com.shop.domains.users.userService.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MyUserDetailService implements UserDetailsService {

    UserService userService;

    public MyUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        return new MyUserDetails(userService.findByName(firstName));
    }
}
