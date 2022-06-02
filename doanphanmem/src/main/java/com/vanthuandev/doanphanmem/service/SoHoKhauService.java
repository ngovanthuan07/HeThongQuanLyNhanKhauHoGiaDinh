package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.SoHoKhau;

import java.util.Optional;

public interface SoHoKhauService {
    <S extends SoHoKhau> S save(S entity);

    Optional<SoHoKhau> findById(Integer integer);

    Optional<SoHoKhau> findSoHoKhauBySoHK(int soHK, int tt);
}
