package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.SoHoKhau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SoHoKhauRepository extends JpaRepository<SoHoKhau, Integer> {

    @Query( "select shk from SoHoKhau shk " +
            "inner join NhanKhauThuongTru  nktt on nktt.soHoKhau.soHK = shk.soHK " +
            "where shk.soHK = ?1 and nktt.trangThai = ?2")
    Optional<SoHoKhau> findSoHoKhauBySoHK(int soHK, int tt);
}
