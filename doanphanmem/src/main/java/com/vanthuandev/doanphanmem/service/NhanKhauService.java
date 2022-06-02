package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;

import java.util.List;
import java.util.Optional;

public interface NhanKhauService {

    <S extends NhanKhau> S save(S entity);


    Optional<NhanKhau> findById(Integer integer);

    <S extends NhanKhau> List<S> saveAll(Iterable<S> entities);
}
