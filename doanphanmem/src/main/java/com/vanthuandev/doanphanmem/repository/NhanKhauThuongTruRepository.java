package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NhanKhauThuongTruRepository extends JpaRepository<NhanKhauThuongTru, Integer> {
    @Query( "select nktt, nk, shk from NhanKhauThuongTru as nktt " +
            "inner join NhanKhau as nk on nktt.nhanKhau.maNK = nk.maNK " +
            "inner join SoHoKhau as shk on nktt.soHoKhau.soHK = shk.soHK " +
            "where nktt.trangThai = ?1 and nk.quanHe.maQH = (" +
                                        "select min(my_nk.quanHe.maQH) " +
                                        " from NhanKhauThuongTru my_nktt, NhanKhau my_nk " +
                                        " where my_nktt.soHoKhau.soHK = shk.soHK and my_nktt.nhanKhau.maNK = my_nk.maNK) " +
            "group by nktt.soHoKhau.soHK")
    List<NhanKhauThuongTru> findAllByTrangThai(int trangThai);

    @Query( "select nktt, nk, shk from NhanKhauThuongTru as nktt " +
            "inner join NhanKhau as nk on nktt.nhanKhau.maNK = nk.maNK " +
            "inner join SoHoKhau as shk on nktt.soHoKhau.soHK = shk.soHK " +
            "where nktt.soHoKhau.soHK = ?1 and nktt.trangThai = ?2")
    List<NhanKhauThuongTru> findAllBySoHKAndTrangThai(int soHK, int trangThai);

    @Query( "select nktt, nk, shk from NhanKhauThuongTru as nktt " +
            "inner join NhanKhau as nk on nktt.nhanKhau.maNK = nk.maNK " +
            "inner join SoHoKhau as shk on nktt.soHoKhau.soHK = shk.soHK " +
            "where nktt.nhanKhau.maNK = ?1 and nktt.trangThai = ?1")
    Optional<NhanKhauThuongTru> findNhanKhauThuongTruByNhanKhau(int maNK, int trangThai);

    @Query( "select nktt, nk, shk from NhanKhauThuongTru as nktt " +
            "inner join NhanKhau as nk on nktt.nhanKhau.maNK = nk.maNK " +
            "inner join SoHoKhau as shk on nktt.soHoKhau.soHK = shk.soHK " +
            "where nktt.nhanKhau.maNK = ?1 and nktt.soHoKhau.soHK = ?2 and nktt.trangThai = ?3")
    Optional<NhanKhauThuongTru> findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(int maNK, int soHK, int trangThai);

    @Query( "select nktt, nk, shk from NhanKhauThuongTru as nktt " +
            "inner join NhanKhau as nk on nktt.nhanKhau.maNK = nk.maNK " +
            "inner join SoHoKhau as shk on nktt.soHoKhau.soHK = shk.soHK " +
            "where nk.quanHe.maQH = ( " +
                        "select min(my_nk.quanHe.maQH) " +
                        " from NhanKhauThuongTru my_nktt, NhanKhau my_nk " +
                        " where my_nktt.soHoKhau.soHK = shk.soHK and my_nktt.nhanKhau.maNK = my_nk.maNK) " +
            " and nktt.nhanKhau.hoVaTen like %?1% and nktt.trangThai = ?2 " +
            "group by nktt.soHoKhau.soHK")
    List<NhanKhauThuongTru> search(String keyword, int trangThai);
}
