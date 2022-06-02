package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTruPK;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NhanKhauTamTruRepository extends JpaRepository<NhanKhauTamTru, NhanKhauTamTruPK> {

    @Query("select max(nk_tam_tru.nhanKhauTamTruPK.maTT) from  NhanKhauTamTru nk_tam_tru")
    Integer max();

    @Query(" select nk_tam_tru, nk from NhanKhauTamTru nk_tam_tru " +
                                "inner join NhanKhau nk on nk.maNK = nk_tam_tru.nhanKhauTamTruPK.maNK " +
            "where nk_tam_tru.trangThai = ?1 and nk.quanHe.maQH = " +
                                                        "(select min(my_nk.quanHe.maQH) " +
                                                        " from NhanKhauTamTru nktt, NhanKhau my_nk" +
                                                        " where nktt.nhanKhauTamTruPK.maTT = nk_tam_tru.nhanKhauTamTruPK.maTT and nktt.nhanKhauTamTruPK.maNK = my_nk.maNK) " +
            "group by nk_tam_tru.nhanKhauTamTruPK.maTT")
    List<NhanKhauTamTru> findAllByNhanKhauTamTruByTrangThai(int trangThai);


    @Query(" select nk_tam_tru, nk from NhanKhauTamTru nk_tam_tru " +
            "inner join NhanKhau nk on nk.maNK = nk_tam_tru.nhanKhauTamTruPK.maNK " +
            "where nk_tam_tru.nhanKhauTamTruPK.maTT = ?1 and nk_tam_tru.trangThai = ?2")
    List<NhanKhauTamTru> findNhanKhauTamTruByMaTTAndTrangThai(int maTT, int trangThai);


    @Query(" select nk_tam_tru, nk from NhanKhauTamTru nk_tam_tru " +
            "inner join NhanKhau nk on nk.maNK = nk_tam_tru.nhanKhauTamTruPK.maNK " +
            "where nk_tam_tru.nhanKhauTamTruPK.maTT = ?1 and nk_tam_tru.nhanKhauTamTruPK.maNK = ?2")
    Optional<NhanKhauTamTru> findNhanKhauTamTruByMaTTAndMaNK(int maTT, int maNK);
}
