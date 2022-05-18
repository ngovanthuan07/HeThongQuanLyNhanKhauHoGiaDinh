package com.vanthuandev.doanphanmem.configs;

import com.vanthuandev.doanphanmem.handlers.LoginSuccessHandler;
import com.vanthuandev.doanphanmem.handlers.LogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SuccessHandler {
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutHandler() {
        return new LogoutHandler();
    }
}
