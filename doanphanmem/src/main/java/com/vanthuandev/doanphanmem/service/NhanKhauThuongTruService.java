package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;

import java.util.List;
import java.util.Optional;

public interface NhanKhauThuongTruService {
    <S extends NhanKhauThuongTru> S save(S entity);

    List<NhanKhauThuongTru> findAllByTrangThai(int trangThai);

    List<NhanKhauThuongTru> findAll();

    List<NhanKhauThuongTru> findAllBySoHKAndTrangThai(int soHK, int trangThai);

    Optional<NhanKhauThuongTru> findNhanKhauThuongTruByNhanKhau(int maNK, int trangThai);

    Optional<NhanKhauThuongTru> findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(int maNK, int soHK, int trangThai);

    Optional<NhanKhauThuongTru> findById(Integer integer);

    <S extends NhanKhauThuongTru> List<S> saveAll(Iterable<S> entities);
}
