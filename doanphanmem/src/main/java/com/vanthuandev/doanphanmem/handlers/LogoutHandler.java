package com.vanthuandev.doanphanmem.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vanthuandev.doanphanmem.constants.ApplicationRole.*;
import static com.vanthuandev.doanphanmem.constants.ApplicationRole.ROLE_USER;

public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
//        Lay vai tro
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

//        Xoa session
        request.getSession().removeAttribute("currentUser");

//        Kiem tra dieu huong
        if (roles.contains(ROLE_ADMIN.name()))
            response.sendRedirect("/admin-panel/login?logout");
        else if (roles.contains(ROLE_POLICE.name()))
            response.sendRedirect("/congan-panel/login?logout");
        else if (roles.contains(ROLE_OFFICER.name()))
            response.sendRedirect("/canbo-panel/login?logout");
        else if (roles.contains(ROLE_USER.name()))
            response.sendRedirect("/nguoidung-panel/login?logout");
    }
}
