package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    @Query("select nd from NguoiDung  nd where nd.username = ?1")
    Optional<NguoiDung> findNguoiDungByUsername(String username);

    @Query("select nd from NguoiDung  nd where nd.email = ?1")
    Optional<NguoiDung> findNguoiDungByEmail(String email);
}
