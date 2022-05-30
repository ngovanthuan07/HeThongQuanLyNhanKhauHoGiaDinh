package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.DanToc;

import java.util.List;

public interface DanTocService {
    <S extends DanToc> S save(S entity);

    <S extends DanToc> List<S> saveAll(Iterable<S> entities);

    List<DanToc> findAll();
}
