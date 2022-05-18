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
import static com.vanthuandev.doanphanmem.constants.ApplicationRole.ROLE_POLICE;
import static com.vanthuandev.doanphanmem.utils.ApplicationFormatRole.hasRole;

@Configuration
@EnableWebSecurity
@Order(2)
public class PoliceSecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("nguoiDungService")
    @Autowired
    private UserDetailsService congAnService;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(congAnService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/congan/**")
                .authorizeRequests()
                .antMatchers("/congan/**")
                .access(hasRole(ROLE_POLICE.name()))
                .and()
                .formLogin().loginPage("/congan-panel")
                .loginProcessingUrl("/congan/process-login")
                .defaultSuccessUrl("/congan-panel/welcome")
                .failureUrl("/congan-panel/login?error")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .and().logout().logoutUrl("/congan/process-logout")
                .logoutSuccessHandler(logoutHandler)
                .and()
                .exceptionHandling().accessDeniedPage("/congan-panel/accessDenied");
        http.cors().and().csrf().disable();
    }
}
