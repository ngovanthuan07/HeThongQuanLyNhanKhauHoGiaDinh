package com.vanthuandev.doanphanmem.controllers.police;


import com.google.gson.Gson;
import com.vanthuandev.doanphanmem.model.Base;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.pojos.SoHoKhau;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.NhanKhauThuongTruService;
import com.vanthuandev.doanphanmem.service.SoHoKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/congan/api**")
public class ApiPoliceController {
    @Autowired
    private SoHoKhauService soHoKhauService;

    @Autowired
    private NhanKhauService nhanKhauService;

    @Autowired
    private NhanKhauThuongTruService nhanKhauThuongTruService;


    @PostMapping(path = "/add-sohokhau", produces = {
                MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<String> addNhanKhau(@RequestBody SoHoKhau soHoKhau) {
        SoHoKhau shk = soHoKhau;
        if(shk != null) {
            shk.setTrangThai("Oke");
            shk.setNgayCapNhat(new Date());
            SoHoKhau mySHK =  soHoKhauService.save(shk);
            return new ResponseEntity<>(new Gson().toJson(mySHK), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping(path = "/add-nhankhauthuongtru", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addThuongTru(@RequestBody NhanKhau nhanKhau) {
        if(nhanKhau != null) {
            Optional<SoHoKhau> soHoKhau = soHoKhauService.findById(nhanKhau.get_id());
            if(soHoKhau.isPresent()) {
                NhanKhau nk = nhanKhauService.save(nhanKhau);
                nk.setNgayCapNhat(new Date());
                NhanKhauThuongTru nhanKhauThuongTru = new NhanKhauThuongTru();
                nhanKhauThuongTru.setNhanKhau(nhanKhau);
                nhanKhauThuongTru.setSoHoKhau(soHoKhau.get());
                nhanKhauThuongTru.setNgayCapNhat(new Date());
                nhanKhauThuongTru.setTrangThai(1);
                NhanKhauThuongTru newNKTT = nhanKhauThuongTruService.save(nhanKhauThuongTru);

                return new ResponseEntity<>(new Gson().toJson("Thành Công"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
