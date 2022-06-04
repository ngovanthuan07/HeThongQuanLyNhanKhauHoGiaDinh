package com.vanthuandev.doanphanmem.controllers.police;

import com.vanthuandev.doanphanmem.pojos.DanToc;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.repository.NhanKhauRepository;
import com.vanthuandev.doanphanmem.service.DanTocService;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.NhanKhauTamTruService;
import com.vanthuandev.doanphanmem.service.NhanKhauThuongTruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/congan/loc**")
public class LocController {

    @Autowired
    private DanTocService danTocService;

    @Autowired
    private NhanKhauService nhanKhauService;

    @Autowired
    private NhanKhauThuongTruService nhanKhauThuongTruService;

    @GetMapping
    private String loc(Model model) {
        model.addAttribute("danTocs", danTocService.findAll());
        return "/congan/loc/loc-nhankhau-thuong-tru";
    }


    @GetMapping("/timkiem")
    private String loc(Model model,
                       @RequestParam String hoVaTen,
                       @RequestParam String sinhTuNgay,
                       @RequestParam String sinhDenNgay,
                       @RequestParam String dangKyXaThuongTru,
                       @RequestParam Integer gioiTinh,
                       @RequestParam String dangKyTuNgay,
                       @RequestParam String dangKyDenNgay,
                       @RequestParam String ngheNghiep,
                       @RequestParam String cmnd,
                       @RequestParam Integer danToc) {
        model.addAttribute("danTocs", danTocService.findAll());

        Map<String, String> map = new HashMap<>();
        map.put("hoVaTen", hoVaTen);
        map.put("sinhTuNgay", sinhTuNgay);
        map.put("sinhDenNgay", sinhDenNgay);
        map.put("dangKyXaThuongTru", dangKyXaThuongTru);
        map.put("gioiTinh", gioiTinh > 0 ? String.valueOf(gioiTinh) : null);
        map.put("dangKyTuNgay", dangKyTuNgay);
        map.put("dangKyDenNgay", dangKyDenNgay);
        map.put("ngheNghiep", ngheNghiep);
        map.put("cmnd", cmnd);
        map.put("danToc", danToc > 0 ? String.valueOf(danToc) : null);
        List<NhanKhauThuongTru> nhanKhauThuongTruList = nhanKhauThuongTruService.filterNhanKhau(map);
        model.addAttribute("listNKTT", nhanKhauThuongTruList);
        model.addAttribute("ketQua", nhanKhauThuongTruList.size());
        return "/congan/loc/loc-nhankhau-thuong-tru-tim-kiem";
    }
}
