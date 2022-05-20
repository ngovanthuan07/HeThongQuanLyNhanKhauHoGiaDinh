package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.ChiTiet;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.repository.NhanKhauRepository;
import com.vanthuandev.doanphanmem.service.ChiTietService;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NhanKhauServiceImpl implements NhanKhauService {

    @Autowired
    private NhanKhauRepository nhanKhauRepository;

    @Autowired
    private ChiTietService chiTietService;

    @Override
    public NhanKhau save(NhanKhau nhanKhau) {
        List<ChiTiet> chiTiets = (List<ChiTiet>) nhanKhau.getChiTiets();
        nhanKhau.setChiTiets(null);
        NhanKhau newNhanKhau = nhanKhauRepository.save(nhanKhau);
        chiTiets.forEach(ct -> ct.setNhanKhau(newNhanKhau));
        newNhanKhau.setChiTiets(chiTietService.saveAll(chiTiets));
        return newNhanKhau;
    }



}
