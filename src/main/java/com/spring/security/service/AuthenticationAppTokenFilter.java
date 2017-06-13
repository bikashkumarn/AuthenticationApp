package com.spring.security.service;

import com.spring.controller.AuthenticationController;
import com.spring.security.util.AuthenticationTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 10/06/17.
 */
public class AuthenticationAppTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Value("${auth.token.header}")
    private String authTokenHeader;

    @Autowired
    private AuthenticationUserDetails authenticationUserDetails;

    @Autowired
    private AuthenticationTokenUtil authenticationTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        logger.info("Entered doFilterInternal()...");

        String authToken = request.getHeader(authTokenHeader);
        logger.info("Auth Token :: " + authToken);

        if(authToken != null && !authToken.isEmpty()) {
            String username = authenticationTokenUtil.getUsernameFromToken(authToken);
            logger.info("Username :: " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = authenticationUserDetails.loadUserByUsername(username);
                if (authenticationTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        logger.info("Redirecting to controller...");
        chain.doFilter(request, response);
    }
}
