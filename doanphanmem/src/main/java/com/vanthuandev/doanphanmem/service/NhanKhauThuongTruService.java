package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;

import java.util.List;

public interface NhanKhauThuongTruService {
    <S extends NhanKhauThuongTru> S save(S entity);

    List<NhanKhauThuongTru> findAllByTrangThai(int trangThai);

    List<NhanKhauThuongTru> findAllBySoHKAndTrangThai(int soHK, int trangThai);
}
