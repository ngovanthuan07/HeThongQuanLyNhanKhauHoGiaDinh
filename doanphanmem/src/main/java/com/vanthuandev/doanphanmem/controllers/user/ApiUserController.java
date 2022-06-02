package com.vanthuandev.doanphanmem.controllers.user;

import com.google.gson.Gson;
import com.vanthuandev.doanphanmem.constants.ApplicationNhanKhau;
import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;
import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.QuanHeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

import static com.vanthuandev.doanphanmem.constants.ApplicationNhanKhau.*;

@RestController
@RequestMapping("/nguoidung/api**")
public class ApiUserController {

    @Autowired
    private DangKyHoSoService dangKyHoSoService;

    @PostMapping(path = "send-ho-so",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> themHoSo(@RequestBody DangKyHoSo dangKyHoSo, HttpSession httpSession) {
        if(dangKyHoSo != null) {
            dangKyHoSo.setTrangThai(1);
            dangKyHoSo.setNgayDangKy(new Date());
            NguoiDung nguoiTao = (NguoiDung) httpSession.getAttribute("currentUser");
            dangKyHoSo.setNguoiTao(nguoiTao);
            dangKyHoSoService.save(dangKyHoSo);
            return new ResponseEntity<>(new Gson().toJson("Success"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Gson().toJson("Error") , HttpStatus.BAD_REQUEST);
    }
}
