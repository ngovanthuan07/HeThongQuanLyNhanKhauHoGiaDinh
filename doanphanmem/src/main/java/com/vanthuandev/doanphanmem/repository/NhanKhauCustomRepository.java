package com.vanthuandev.doanphanmem.repository;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;

import java.util.List;
import java.util.Map;

public interface NhanKhauCustomRepository {
    List<NhanKhauThuongTru> filterNhanKhauThuongTru(Map<String, String> map);

    List<NhanKhauTamTru> filterNhanKhauTamTru(Map<String, String> map);
}
