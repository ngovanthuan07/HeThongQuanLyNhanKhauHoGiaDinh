package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;

import java.util.List;
import java.util.Optional;

public interface DangKyHoSoService {
    <S extends DangKyHoSo> S save(S entity);

    <S extends DangKyHoSo> List<S> saveAll(Iterable<S> entities);

    List<DangKyHoSo> findAllById(Iterable<Integer> integers);

    Optional<DangKyHoSo> findById(Integer integer);

    List<DangKyHoSo> findAllByMaLHSAndTrangThai(int maLHS, int trangThai);
}
