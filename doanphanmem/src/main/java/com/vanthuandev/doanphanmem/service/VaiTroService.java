package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.VaiTro;

import java.util.List;

public interface VaiTroService {
    List<VaiTro> findAll();
    VaiTro findById(int maVaiTro);
}
