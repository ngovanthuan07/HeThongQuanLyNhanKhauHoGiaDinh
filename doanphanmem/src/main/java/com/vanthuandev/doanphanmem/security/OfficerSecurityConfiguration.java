package com.vanthuandev.doanphanmem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static com.vanthuandev.doanphanmem.constants.ApplicationRole.ROLE_ADMIN;
import static com.vanthuandev.doanphanmem.constants.ApplicationRole.ROLE_OFFICER;
import static com.vanthuandev.doanphanmem.utils.ApplicationFormatRole.hasRole;

@Configuration
@EnableWebSecurity
@Order(3)
public class OfficerSecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("nguoiDungService")
    @Autowired
    private UserDetailsService canBoService;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(canBoService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/canbo/**")
                .authorizeRequests()
                .antMatchers("/canbo/**")
                .access(hasRole(ROLE_OFFICER.name()))
                .and()
                .formLogin().loginPage("/canbo-panel")
                .loginProcessingUrl("/canbo/process-login")
                .defaultSuccessUrl("/canbo-panel/welcome")
                .failureUrl("/canbo-panel/login?error")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .and().logout().logoutUrl("/canbo/process-logout")
                .logoutSuccessHandler(logoutHandler)
                .and()
                .exceptionHandling().accessDeniedPage("/canbo-panel/accessDenied");
        http.cors().and().csrf().disable();
    }
}
