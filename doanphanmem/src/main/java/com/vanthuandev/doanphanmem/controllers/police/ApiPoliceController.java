package com.vanthuandev.doanphanmem.controllers.police;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vanthuandev.doanphanmem.model.Base;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.pojos.SoHoKhau;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.NhanKhauThuongTruService;
import com.vanthuandev.doanphanmem.service.SoHoKhauService;
import com.vanthuandev.doanphanmem.utils.NhanKhauThuongTruUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/congan/api**")
public class ApiPoliceController {
    @Autowired
    private SoHoKhauService soHoKhauService;

    @Autowired
    private NhanKhauService nhanKhauService;

    @Autowired
    private NhanKhauThuongTruService nhanKhauThuongTruService;

    // Thường trú
    @PostMapping(path = "/add-sohokhau", produces = {MediaType.APPLICATION_JSON_VALUE})
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

                return new ResponseEntity<>(new Gson().toJson(String.format("Success|%s", newNKTT.getSoHoKhau().getSoHK())), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/edit-nhankhauthuongtru", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> editThuongTru(@RequestBody NhanKhau nhanKhau) {
        if(nhanKhau != null) {
            NhanKhau nk = nhanKhauService.save(nhanKhau);
            return new ResponseEntity<>(new Gson().toJson("Success"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Gson().toJson("Error"),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/delete-nhankhauthuongtru", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteThuongTru(@RequestBody Map<String,String> param) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,String> newP = param;
        String lyDo = param.getOrDefault("lyDo", null);
        String ngayCapNhat = param.getOrDefault("ngayCapNhat", null);
        String maNKTT = param.getOrDefault("maNKTT", null);
        String noiChuyenDen = param.getOrDefault("noiChuyenDen", null);
        Date date = null;
        if(lyDo != null && ngayCapNhat != null && maNKTT != null ) {
            try {
                date = sdf.parse(ngayCapNhat);
                int id = Integer.parseInt(maNKTT);
                NhanKhauThuongTru nktt = nhanKhauThuongTruService.findById(id).get();
                nktt.setNgayCapNhat(date);
                nktt.setTrangThai(0);
                nktt.setLyDo(lyDo);
                nktt.setNoiChuyenDen(noiChuyenDen);
                nktt.setChuThich("Xoá nhân khẩu thường trú");
                NhanKhauThuongTru nhanKhauTT = nhanKhauThuongTruService.save(nktt);
               return new ResponseEntity<>(String.valueOf(nhanKhauTT.getSoHoKhau().getSoHK()),HttpStatus.OK);
            } catch (ParseException ex) {
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }


    @PostMapping(path = "/tach-nhankhauthuongtru", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> splitThuongTru(@RequestBody Map<String,String> param) {
        int soHK = Integer.parseInt(param.get("soHK"));
        SoHoKhau soHoKhau = new Gson().fromJson(param.get("soHoKhau"), SoHoKhau.class);
        Type type = new TypeToken<List<NhanKhau>>(){}.getType();
        List<NhanKhau> nhanKhaus = new Gson().fromJson(param.get("nhanKhaus"), type);
        if (soHoKhau != null && nhanKhaus != null) {
            soHoKhau.setNgayCapNhat(new Date());
            soHoKhau.setTrangThai("Oke");
            // ========== Sử đổi trạng thái của nhân khẩu ==========
            for(NhanKhau nk : nhanKhaus) {
                Optional<NhanKhauThuongTru> nkTT = nhanKhauThuongTruService
                        .findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(nk.getMaNK(),soHK,1);
                if(nkTT.isPresent()) {
                    NhanKhauThuongTru nhanKhauThuongTru = nkTT.get();
                    nhanKhauThuongTru.setTrangThai(0);
                    nhanKhauThuongTru.setChuThich("Đã tách hộ khẩu");
                    nhanKhauThuongTru.setNgayCapNhat(new Date());
                    nhanKhauThuongTru.setLyDo("Tách hộ khẩu");
                    nhanKhauThuongTruService.save(nhanKhauThuongTru);
                }
            }
            // ========== Thêm sổ hộ khẩu ==========
            SoHoKhau soHoKhau1 = soHoKhauService.save(soHoKhau);
            List<NhanKhauThuongTru> nhanKhauThuongTrus = new ArrayList<>();
            for(NhanKhau nk : nhanKhaus) {
                Optional<NhanKhau> nhanKhauOptional = nhanKhauService.findById(nk.getMaNK());
                if(nhanKhauOptional.isPresent()) {
                    NhanKhau isNhanKhau = nhanKhauOptional.get();
                    isNhanKhau.setNgayCapNhat(new Date());
                    NhanKhauThuongTru nhanKhauThuongTru = new NhanKhauThuongTru();
                    nhanKhauThuongTru.setNhanKhau(isNhanKhau);
                    nhanKhauThuongTru.setSoHoKhau(soHoKhau1);
                    nhanKhauThuongTru.setNgayCapNhat(new Date());
                    nhanKhauThuongTru.setTrangThai(1);
                    nhanKhauThuongTrus.add(nhanKhauThuongTru);
                }
            }
            if (!nhanKhauThuongTrus.isEmpty()) {
                nhanKhauThuongTruService.saveAll(nhanKhauThuongTrus);
            }

            return new ResponseEntity<>(new Gson().toJson("Success"),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Gson().toJson("Error"),HttpStatus.BAD_REQUEST);
    }
}
