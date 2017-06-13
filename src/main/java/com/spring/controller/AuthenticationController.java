package com.spring.controller;

import com.spring.security.entity.AuthenticationRequestDTO;
import com.spring.security.entity.AuthenticationResponseDTO;
import com.spring.security.entity.UserToken;
import com.spring.security.service.AuthenticationUserDetails;
import com.spring.security.util.AuthenticationTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by apple on 11/06/17.
 */
@RestController
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationTokenUtil authenticationTokenUtil;

    @Autowired
    private AuthenticationUserDetails authenticationUserDetails;

    @RequestMapping(value = "/authenticate/user", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO)
            throws AuthenticationException {

        logger.info("Entered createAuthenticationToken()....");

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDTO.getUsername(),
                        authenticationRequestDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = authenticationUserDetails.loadUserByUsername(authenticationRequestDTO.getUsername());
        final String token = authenticationTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

}
