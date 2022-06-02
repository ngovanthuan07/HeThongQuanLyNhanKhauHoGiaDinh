package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DangKyHoSoRepository extends JpaRepository<DangKyHoSo, Integer> {
    @Query(" select dkhs, lhs from DangKyHoSo dkhs " +
                        "inner join LoaiHoSo lhs on lhs.maLHS = dkhs.loaiHoSo.maLHS " +
            "where dkhs.loaiHoSo.maLHS = ?1 and dkhs.trangThai = ?2")
    List<DangKyHoSo> findAllByMaLHSAndTrangThai(int maLHS, int trangThai);
}
