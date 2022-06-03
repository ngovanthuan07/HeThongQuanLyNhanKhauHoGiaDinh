package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface NguoiDungService extends UserDetailsService {
    NguoiDung getNguoiDungById(int nguoiDungId);
    String addNguoiDung(NguoiDung nguoiDung);

    Page<NguoiDung> findNguoiDungByUsernameOrCmndContaining(String username, String cmnd, Pageable pageable);

    Page<NguoiDung> findNguoiDungByUsernameContaining(String username, Pageable pageable);

    Page<NguoiDung> findNguoiDungByCmndContaining(String cmnd, Pageable pageable);

    Page<NguoiDung> findAll(Pageable pageable);

    String updateNguoiDung(NguoiDung nguoiDung, String keyword);
    NguoiDung getNguoiDung(String username);

    Page<NguoiDung> findNguoiDungByUsernameAndTrangThaiContaining(String username, int trangThai, Pageable pageable);

    Page<NguoiDung> findNguoiDungByTrangThaiContaining(int trangThai, Pageable pageable);

    List<NguoiDung> findAll();

    NguoiDung updateUser(NguoiDung nguoiDung);
}
