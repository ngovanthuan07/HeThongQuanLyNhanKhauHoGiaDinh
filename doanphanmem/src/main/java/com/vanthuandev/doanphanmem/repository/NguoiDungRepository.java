package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    @Query("select nd from NguoiDung  nd where nd.username = ?1")
    Optional<NguoiDung> findNguoiDungByUsername(String username);

    @Query("select nd from NguoiDung  nd where nd.email = ?1")
    Optional<NguoiDung> findNguoiDungByEmail(String email);


    Page<NguoiDung> findNguoiDungByUsernameContaining(String username, Pageable pageable);

    Page<NguoiDung> findNguoiDungByCmndContaining(String cmnd, Pageable pageable);

    Page<NguoiDung> findNguoiDungByUsernameOrCmndContaining(String username, String cmnd, Pageable pageable);

    @Query("select nd from NguoiDung nd where nd.username like %?1% and nd.trangThai = ?2")
    Page<NguoiDung> findNguoiDungByUsernameAndTrangThaiContaining(String username, int trangThai, Pageable pageable);

    @Query("select nd from NguoiDung nd where nd.trangThai = ?1")
    Page<NguoiDung> findNguoiDungByTrangThaiContaining(int trangThai, Pageable pageable);
}
