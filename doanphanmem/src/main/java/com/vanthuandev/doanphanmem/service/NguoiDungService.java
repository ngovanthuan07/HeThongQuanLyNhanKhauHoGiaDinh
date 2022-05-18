package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface NguoiDungService extends UserDetailsService {
    NguoiDung getNguoiDungById(int nguoiDungId);
    String addNguoiDung(NguoiDung nguoiDung);
    String updateNguoiDung(NguoiDung nguoiDung, String keyword);
    NguoiDung getNguoiDung(String username);
    List<NguoiDung> findAll();
}
