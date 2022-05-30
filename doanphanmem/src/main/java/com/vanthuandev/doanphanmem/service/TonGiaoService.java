package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.TonGiao;

import java.util.List;

public interface TonGiaoService {
    <S extends TonGiao> S save(S entity);

    <S extends TonGiao> List<S> saveAll(Iterable<S> entities);

    List<TonGiao> findAll();
}
