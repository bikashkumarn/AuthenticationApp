package com.spring.config;

import com.spring.security.service.AuthenticationAppEntryPoint;
import com.spring.security.service.AuthenticationAppTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by apple on 10/06/17.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationAppEntryPoint authenticationAppEntryPoint;

    @Bean
    public AuthenticationAppTokenFilter authenticationAppTokenFilter() {
        AuthenticationAppTokenFilter authenticationAppTokenFilter = new AuthenticationAppTokenFilter();
        return authenticationAppTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationAppEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/authenticate/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(
                authenticationAppTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().cacheControl();
    }

}
