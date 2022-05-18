package com.vanthuandev.doanphanmem.handlers;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import com.vanthuandev.doanphanmem.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vanthuandev.doanphanmem.constants.ApplicationRole.*;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
            //        Lay vai tro
            Set<String> roles = authentication.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());
//        Ly nguoi dung dua vao session
            NguoiDung nguoiDung = nguoiDungService.getNguoiDung(authentication.getName());
            request.getSession().setAttribute("currentUser", nguoiDung);
//        Kiem tra dieu huong
            if (roles.contains(ROLE_ADMIN.name()))
                response.sendRedirect("/admin");
            else if (roles.contains(ROLE_POLICE.name()))
                response.sendRedirect("/congan");
            else if (roles.contains(ROLE_OFFICER.name()))
                response.sendRedirect("/canbo");
            else if (roles.contains(ROLE_USER.name()))
                response.sendRedirect("/nguoidung");

    }
}
