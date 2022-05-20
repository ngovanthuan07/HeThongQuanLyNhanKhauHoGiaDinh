package com.vanthuandev.doanphanmem.controllers.user;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class ApiNguoiDungController {

    @PostMapping(path = "/nguoidung/api/add-nhankhau", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<NhanKhau> addNhanKhau(@RequestBody Map<String, String> params, HttpSession httpSession) {
        System.out.println(params.get("nhanKhau"));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
