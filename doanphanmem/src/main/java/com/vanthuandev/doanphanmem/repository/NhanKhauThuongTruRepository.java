package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NhanKhauThuongTruRepository extends JpaRepository<NhanKhauThuongTru, Integer> {
    @Query( "select nktt, nk, shk from NhanKhauThuongTru as nktt " +
            "inner join NhanKhau as nk on nktt.nhanKhau.maNK = nk.maNK " +
            "inner join SoHoKhau as shk on nktt.soHoKhau.soHK = shk.soHK " +
            "where nktt.trangThai = ?1 and nk.quanHe.maQH = " +
                                        "(select min(qh.maQH) from QuanHe qh)" +
            "group by nktt.soHoKhau.soHK")
    List<NhanKhauThuongTru> findAllByTrangThai(int trangThai);

    @Query( "select nktt, nk, shk from NhanKhauThuongTru as nktt " +
            "inner join NhanKhau as nk on nktt.nhanKhau.maNK = nk.maNK " +
            "inner join SoHoKhau as shk on nktt.soHoKhau.soHK = shk.soHK " +
            "where nktt.soHoKhau.soHK = ?1 and nktt.trangThai = ?1")
    List<NhanKhauThuongTru> findAllBySoHKAndTrangThai(int soHK, int trangThai);
}
