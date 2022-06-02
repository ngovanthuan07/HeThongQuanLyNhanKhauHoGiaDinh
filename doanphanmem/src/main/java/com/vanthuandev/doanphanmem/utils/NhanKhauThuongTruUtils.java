package com.vanthuandev.doanphanmem.utils;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.pojos.SoHoKhau;
import com.vanthuandev.doanphanmem.repository.NhanKhauThuongTruRepository;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.NhanKhauThuongTruService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NhanKhauThuongTruUtils {
    private static NhanKhauThuongTruUtils nhanKhauThuongTruUtil = null;

    public static NhanKhauThuongTruUtils getInstance() {
        if(nhanKhauThuongTruUtil == null) {
            nhanKhauThuongTruUtil = new NhanKhauThuongTruUtils();
        }
        return nhanKhauThuongTruUtil;
    }

    @Autowired
    private NhanKhauThuongTruRepository nhanKhauThuongTruRepository;

    @Autowired
    private NhanKhauService nhanKhauService;

    public void tachHoKhau(List<NhanKhau> nhanKhaus, int soHK) {
        for(NhanKhau nhanKhau : nhanKhaus) {
            Optional<NhanKhauThuongTru> nkTT = nhanKhauThuongTruRepository.findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(1,1,1);
            String a = "Oke";
            if(nkTT.isPresent()) {
                NhanKhauThuongTru nhanKhauThuongTru = nkTT.get();
                nhanKhauThuongTru.setTrangThai(0);
                nhanKhauThuongTru.setChuThich("Đã tách hộ khẩu");
                nhanKhauThuongTru.setNgayCapNhat(new Date());
                nhanKhauThuongTru.setLyDo("Tách hộ khẩu");
                nhanKhauThuongTruRepository.save(nhanKhauThuongTru);
            }
        }
    }

    public void themNhanKhauMoi(List<NhanKhau> nhanKhaus, SoHoKhau soHoKhau) {
        List<NhanKhauThuongTru> nhanKhauThuongTrus = null;
        for(NhanKhau nhanKhau : nhanKhaus) {
            Optional<NhanKhau> nhanKhauOptional = nhanKhauService.findById(nhanKhau.getMaNK());
            if(nhanKhauOptional.isPresent()) {
                NhanKhau isNhanKhau = nhanKhauOptional.get();
                isNhanKhau.setNgayCapNhat(new Date());
                NhanKhauThuongTru nhanKhauThuongTru = new NhanKhauThuongTru();
                nhanKhauThuongTru.setNhanKhau(isNhanKhau);
                nhanKhauThuongTru.setSoHoKhau(soHoKhau);
                nhanKhauThuongTru.setNgayCapNhat(new Date());
                nhanKhauThuongTru.setTrangThai(1);
                nhanKhauThuongTrus.add(nhanKhauThuongTru);
            }
        }
        if (!nhanKhauThuongTrus.isEmpty()) {
            nhanKhauThuongTruRepository.saveAll(nhanKhauThuongTrus);
        }
    }
}
