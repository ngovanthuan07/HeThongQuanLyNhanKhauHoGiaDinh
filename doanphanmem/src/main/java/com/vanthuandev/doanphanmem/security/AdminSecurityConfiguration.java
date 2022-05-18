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
import static com.vanthuandev.doanphanmem.utils.ApplicationFormatRole.hasRole;

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("nguoiDungService")
    @Autowired
    private UserDetailsService adminService;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .antMatchers("/admin/**")
                .access(hasRole(ROLE_ADMIN.name()))
                .and()
                .formLogin().loginPage("/admin-panel")
                .loginProcessingUrl("/admin/process-login")
                .defaultSuccessUrl("/admin-panel/welcome")
                .failureUrl("/admin-panel/login?error")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .and().logout().logoutUrl("/admin/process-logout")
                .logoutSuccessHandler(logoutHandler)
                .and()
                .exceptionHandling().accessDeniedPage("/admin-panel/accessDenied");
        http.cors().and().csrf().disable();
    }
}
