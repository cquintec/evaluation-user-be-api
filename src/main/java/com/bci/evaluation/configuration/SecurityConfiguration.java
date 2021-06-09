package com.bci.evaluation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bci.evaluation.filters.JwtRequestFilter;
import com.bci.evaluation.service.impl.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService customerUserDetailService;
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf()
       .disable()
       .authorizeRequests()
       .antMatchers("/api/v1/auth/token")
       .permitAll()
       .anyRequest()
       .authenticated()
       .and().sessionManagement()
       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
 
        try {
            auth.userDetailsService(customerUserDetailService);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
}
