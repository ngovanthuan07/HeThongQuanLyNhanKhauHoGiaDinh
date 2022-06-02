package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;

import java.util.List;
import java.util.Optional;

public interface NhanKhauTamTruService {
    <S extends NhanKhauTamTru> S save(S entity);

    <S extends NhanKhauTamTru> List<S> saveAll(Iterable<S> entities);

    Integer max();


    List<NhanKhauTamTru> findAllByNhanKhauTamTruByTrangThai(int trangThai);

    List<NhanKhauTamTru> findNhanKhauTamTruByMaTTAndTrangThai(int maTT, int trangThai);

    Optional<NhanKhauTamTru> findNhanKhauTamTruByMaTTAndMaNK(int maTT, int maNK);
}
