package com.spring.controller;

import com.spring.security.entity.UserToken;
import com.spring.security.service.AuthenticationUserDetails;
import com.spring.security.util.AuthenticationTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by apple on 23/05/17.
 */
@RestController
public class LoginController {

    @Value("${auth.token.header}")
    private String authTokenHeader;

    @Autowired
    private AuthenticationTokenUtil authenticationTokenUtil;

    @Autowired
    private AuthenticationUserDetails authenticationUserDetails;


    @RequestMapping(value="/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminMessage() {
        return ResponseEntity.ok("Greetings Mr admin !!!");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public UserToken getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(authTokenHeader);
        String username = authenticationTokenUtil.getUsernameFromToken(token);
        UserToken userToken = (UserToken) authenticationUserDetails.loadUserByUsername(username);
        return userToken;
    }

}
