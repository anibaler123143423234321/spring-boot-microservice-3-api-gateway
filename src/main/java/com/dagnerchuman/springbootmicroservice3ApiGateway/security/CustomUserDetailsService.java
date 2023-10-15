package com.dagnerchuman.springbootmicroservice3ApiGateway.security;


import com.dagnerchuman.springbootmicroservice3ApiGateway.model.User;
import com.dagnerchuman.springbootmicroservice3ApiGateway.service.UserService;
//import com.dagnerchuman.springbootmicroservice3apigateway.utils.SecurityUtils;
import com.dagnerchuman.springbootmicroservice3ApiGateway.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("El usuario no fue encontrado:"+username));


        Set<GrantedAuthority> authorities = Collections.singleton(SecurityUtils.convertToAuthority(user.getRole().name()));


       return UserPrincipal.builder()
               .user(user)
               .id(user.getId())
               .username(username)
               .password(user.getPassword())
               .authorities(authorities)
               .build();
    }
}
