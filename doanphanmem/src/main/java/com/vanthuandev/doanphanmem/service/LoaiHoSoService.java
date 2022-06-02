package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.LoaiHoSo;

import java.util.List;
import java.util.Optional;

public interface LoaiHoSoService {
    <S extends LoaiHoSo> List<S> saveAll(Iterable<S> entities);

    List<LoaiHoSo> findAll();

    Optional<LoaiHoSo> findById(Integer integer);
}
