package com.spring.security.service;

import com.spring.security.entity.UserToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by apple on 13/06/17.
 */
@Service
public class AuthenticationUserDetails implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Need to get the User Details from the DB and create UserToken object
        UserToken userToken = new UserToken(username, username);
        return userToken;
    }
}
