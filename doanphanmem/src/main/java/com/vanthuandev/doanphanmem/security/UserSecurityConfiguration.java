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
import static com.vanthuandev.doanphanmem.constants.ApplicationRole.ROLE_USER;
import static com.vanthuandev.doanphanmem.utils.ApplicationFormatRole.hasRole;

@Configuration
@EnableWebSecurity
@Order(4)
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("nguoiDungService")
    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/nguoidung/**")
                .authorizeRequests()
                .antMatchers("/nguoidung/**")
                .access(hasRole(ROLE_USER.name()))
                .and()
                .formLogin().loginPage("/nguoidung-panel")
                .loginProcessingUrl("/nguoidung/process-login")
                .defaultSuccessUrl("/nguoidung-panel/welcome")
                .failureUrl("/nguoidung-panel/login?error")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .and().logout().logoutUrl("/nguoidung/process-logout")
                .logoutSuccessHandler(logoutHandler)
                .and()
                .exceptionHandling().accessDeniedPage("/nguoidung-panel/accessDenied");
        http.cors().and().csrf().disable();
    }
}
