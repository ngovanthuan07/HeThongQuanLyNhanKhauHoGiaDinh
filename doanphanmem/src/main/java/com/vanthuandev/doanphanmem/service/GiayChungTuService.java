package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.GiayChungTu;
import com.vanthuandev.doanphanmem.pojos.GiayKhaiSinh;

import java.util.List;
import java.util.Optional;

public interface GiayChungTuService {
    List<GiayChungTu> findAll();

    List<GiayChungTu> findAllById(Iterable<Integer> integers);

    <S extends GiayChungTu> S save(S entity);

    Optional<GiayChungTu> findById(Integer integer);

    void deleteById(Integer integer);

    List<GiayChungTu> findAllGiayChungTuByHoTenKhaiSinh(String keyword);
}
