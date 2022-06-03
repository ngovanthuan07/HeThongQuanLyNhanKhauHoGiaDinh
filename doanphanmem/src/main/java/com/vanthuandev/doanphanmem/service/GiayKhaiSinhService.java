package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.GiayKhaiSinh;

import java.util.List;
import java.util.Optional;

public interface GiayKhaiSinhService {
    <S extends GiayKhaiSinh> S save(S entity);

    Optional<GiayKhaiSinh> findById(Integer integer);

    List<GiayKhaiSinh> findAll();

    void deleteById(Integer integer);
}
