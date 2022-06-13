package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.GiayKhaiSinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiayKhaiSinhRepository extends JpaRepository<GiayKhaiSinh, Integer> {
    @Query("select gks  from GiayKhaiSinh gks where gks.hoTenKhaiSinh like %?1%")
    List<GiayKhaiSinh> findAllGiayKhaiSinhByHoTenKhaiSinh(String keyword);
}
