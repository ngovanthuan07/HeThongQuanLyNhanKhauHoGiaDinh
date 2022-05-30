package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;

public interface NhanKhauService {

    <S extends NhanKhau> S save(S entity);
}
