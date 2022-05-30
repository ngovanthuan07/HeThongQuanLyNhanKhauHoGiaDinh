package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.QuanHe;

import java.util.List;

public interface QuanHeService {
    <S extends QuanHe> S save(S entity);

    <S extends QuanHe> List<S> saveAll(Iterable<S> entities);

    List<QuanHe> findAll();
}
