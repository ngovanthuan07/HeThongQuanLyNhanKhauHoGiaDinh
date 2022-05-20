package com.vanthuandev.doanphanmem.controllers.user;

import com.google.gson.Gson;
import com.vanthuandev.doanphanmem.constants.ApplicationNhanKhau;
import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

import static com.vanthuandev.doanphanmem.constants.ApplicationNhanKhau.*;

@RestController
public class ApiUserController {

    @Autowired
    private NhanKhauService nhanKhauService;

    @PostMapping(path = "/nguoidung/api/add-nhankhau", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<String> addNhanKhau(@RequestBody NhanKhau nhanKhau, HttpSession httpSession) {
        NhanKhau myNhanKhau = nhanKhau;
        if(myNhanKhau != null) {
            NguoiDung nguoiDung = (NguoiDung) httpSession.getAttribute("currentUser");
            myNhanKhau.setNgayCapNhat(new Date());
            myNhanKhau.setTinhTrang(DANG_CHO_XET_DUYET.name());
            myNhanKhau.setNguoiDung(nguoiDung);
            NhanKhau nhanKhau1 =  nhanKhauService.save(myNhanKhau);
            return new ResponseEntity<>(new Gson().toJson("Thành Công"),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
