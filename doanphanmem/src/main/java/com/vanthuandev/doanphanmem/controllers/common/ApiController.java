package com.vanthuandev.doanphanmem.controllers.common;

import com.vanthuandev.doanphanmem.pojos.QuanHe;
import com.vanthuandev.doanphanmem.service.NhanKhauTamTruService;
import com.vanthuandev.doanphanmem.service.QuanHeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiQuanHe {

    @Autowired
    private QuanHeService quanHeService;

    @Autowired
    private NhanKhauTamTruService nhanKhauTamTruService;

    @GetMapping(value = "/api/quanhe")
    public List<QuanHe> all() {
        List<QuanHe> quanHes = quanHeService.findAll();
        quanHes.forEach(quanHe -> {quanHe.setNhanKhaus(null);});
        return quanHes;
    }


    @GetMapping(value = "/api/max-nhan-khau-tam-tru")
    public Integer maxNhanKhauTamTru() {
        return nhanKhauTamTruService.max();
    }
}
