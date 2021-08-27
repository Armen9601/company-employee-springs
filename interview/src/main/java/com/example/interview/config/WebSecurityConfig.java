package com.example.interview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/interviews")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
                .authorizeRequests()
                .antMatchers("/addQuestion","/interview","/interviews","/addAnswer").hasRole("ADMIN")
                .anyRequest().permitAll();
    }
}
