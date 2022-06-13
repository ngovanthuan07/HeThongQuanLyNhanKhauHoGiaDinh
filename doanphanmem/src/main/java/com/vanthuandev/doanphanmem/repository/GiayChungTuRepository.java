package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.GiayChungTu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiayChungTuRepository extends JpaRepository<GiayChungTu, Integer> {
    @Query("select gct from GiayChungTu gct where gct.hoTenKhaiSinh like %?1%")
    List<GiayChungTu> findAllGiayChungTuByHoTenKhaiSinh(String keyword);
}
