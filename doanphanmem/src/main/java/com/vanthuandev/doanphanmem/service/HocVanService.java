package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.HocVan;

import java.util.List;

public interface HocVanService {
    <S extends HocVan> S save(S entity);

    <S extends HocVan> List<S> saveAll(Iterable<S> entities);

    List<HocVan> findAll();
}
