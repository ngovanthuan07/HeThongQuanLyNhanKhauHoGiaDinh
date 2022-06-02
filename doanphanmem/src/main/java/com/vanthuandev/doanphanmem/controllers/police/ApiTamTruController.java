package com.vanthuandev.doanphanmem.controllers.police;

import com.google.gson.Gson;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTruPK;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.NhanKhauTamTruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

import static com.vanthuandev.doanphanmem.constants.ApplicationThuongTru.SO_CA_NHAN;
import static com.vanthuandev.doanphanmem.constants.ApplicationThuongTru.SO_GIA_DINH;

@RestController
@RequestMapping("/congan/api/tamtru**")
public class ApiTamTruController {
    @Autowired
    private NhanKhauService nhanKhauService;

    @Autowired
    private NhanKhauTamTruService nhanKhauTamTruService;

    @PostMapping(path = "/add-tam-tru-ca-nhan", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addTamTruCaNhan(@RequestBody Map<String, String> params) {
        if(params != null) {
            NhanKhau nhanKhau = new Gson().fromJson(params.get("nhanKhau"), NhanKhau.class);
            nhanKhau.setGhiChu("THEM_NHAN_KHAU_THUONG_TRU");
            NhanKhau nhanKhauSave = nhanKhauService.save(nhanKhau);
            NhanKhauTamTru nhanKhauTamTru = new Gson().fromJson(params.get("nhanKhauTamTru"), NhanKhauTamTru.class);
            // tao id cho nhan khau tam tru
            int id = nhanKhauTamTruService.max() + 1;
            nhanKhauTamTru.setNhanKhauTamTruPK(new NhanKhauTamTruPK(id ,nhanKhauSave.getMaNK()));
            nhanKhauTamTru.setNhanKhau(nhanKhauSave);
            nhanKhauTamTru.setLoaiTamTru(SO_CA_NHAN);
            nhanKhauTamTru.setTrangThai(1);
            nhanKhauTamTru.setNgayCapNhat(new Date());
            nhanKhauTamTruService.save(nhanKhauTamTru);
            return new ResponseEntity<>(new Gson().toJson("Success"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Gson().toJson("Error"),HttpStatus.BAD_REQUEST);
    }


    @PostMapping(path = "/add-tam-tru-ho", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addTamTruHo(@RequestBody Map<String, String> params) {
        if(params != null) {
            NhanKhau nhanKhau = new Gson().fromJson(params.get("nhanKhau"), NhanKhau.class);
            nhanKhau.setGhiChu("THEM_NHAN_KHAU_THUONG_TRU");
            NhanKhau nhanKhauSave = nhanKhauService.save(nhanKhau);
            NhanKhauTamTru nhanKhauTamTru = new Gson().fromJson(params.get("nhanKhauTamTru"), NhanKhauTamTru.class);
            // tao id cho nhan khau tam tru
            int id = nhanKhauTamTruService.max() + 1;
            nhanKhauTamTru.setNhanKhauTamTruPK(new NhanKhauTamTruPK(id ,nhanKhauSave.getMaNK()));
            nhanKhauTamTru.setNhanKhau(nhanKhauSave);
            nhanKhauTamTru.setLoaiTamTru(SO_GIA_DINH);
            nhanKhauTamTru.setTrangThai(1);
            nhanKhauTamTru.setNgayCapNhat(new Date());
            NhanKhauTamTru newNKTT = nhanKhauTamTruService.save(nhanKhauTamTru);
            String link = "/congan/tamtru/add-them-thanh-vien-tam-tru?maTT=" + newNKTT.getNhanKhauTamTruPK().getMaTT();
            return new ResponseEntity<>(new Gson().toJson(link), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Gson().toJson("Error"),HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/add-tam-tru-ho-trong-ho", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addTamTruHoTrongHo(@RequestBody Map<String, String> params) {
        if(params != null) {
            Integer id = Integer.parseInt(params.get("maTT"));
            NhanKhau nhanKhau = new Gson().fromJson(params.get("nhanKhau"), NhanKhau.class);
            nhanKhau.setGhiChu("THEM_NHAN_KHAU_THUONG_TRU");
            NhanKhau nhanKhauSave = nhanKhauService.save(nhanKhau);
            NhanKhauTamTru nhanKhauTamTru = new Gson().fromJson(params.get("nhanKhauTamTru"), NhanKhauTamTru.class);
            nhanKhauTamTru.setNhanKhauTamTruPK(new NhanKhauTamTruPK(id,nhanKhauSave.getMaNK()));
            nhanKhauTamTru.setNhanKhau(nhanKhauSave);
            nhanKhauTamTru.setTrangThai(1);
            nhanKhauTamTru.setLoaiTamTru(SO_GIA_DINH);
            nhanKhauTamTru.setNgayCapNhat(new Date());
            NhanKhauTamTru newNKTT = nhanKhauTamTruService.save(nhanKhauTamTru);
            String link = "/congan/tamtru/add-them-thanh-vien-tam-tru?maTT=" + newNKTT.getNhanKhauTamTruPK().getMaTT();
            return new ResponseEntity<>(new Gson().toJson(link), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Gson().toJson("Error"),HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/edit-tam-tru-ho-trong-ho", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> editTamTruHoTrongHo(@RequestBody Map<String, String> params) {
        if(params != null) {
            NhanKhau nhanKhau = new Gson().fromJson(params.get("nhanKhau"), NhanKhau.class);
            NhanKhau nhanKhauSave = nhanKhauService.save(nhanKhau);
            NhanKhauTamTru nhanKhauTamTru = new Gson().fromJson(params.get("nhanKhauTamTru"), NhanKhauTamTru.class);
            NhanKhauTamTru find = nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndMaNK(nhanKhauTamTru.getNhanKhauTamTruPK().getMaTT(), nhanKhauTamTru.getNhanKhauTamTruPK().getMaNK()).get();
            nhanKhauTamTru.setLoaiTamTru(find.getLoaiTamTru());
            nhanKhauTamTru.setTrangThai(1);
            nhanKhauTamTru.setNhanKhau(nhanKhauSave);
            nhanKhauTamTru.setNgayCapNhat(new Date());
            NhanKhauTamTru newNKTT = nhanKhauTamTruService.save(nhanKhauTamTru);
            String link = "/congan/tamtru/danh-sach-chi-tiet-tam-tru?maTT=" + newNKTT.getNhanKhauTamTruPK().getMaTT();
            return new ResponseEntity<>(new Gson().toJson(link), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Gson().toJson("Error"),HttpStatus.BAD_REQUEST);
    }
}
