package com.vanthuandev.doanphanmem.controllers.police;

import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.service.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/congan**")
@Slf4j
public class PoliceController {

    @Autowired
    private DanTocService danTocService;

    @Autowired
    private TonGiaoService tonGiaoService;

    @Autowired
    private QuanHeService quanHeService;

    @Autowired
    private HocVanService hocVanService;

    @Autowired
    private NhanKhauThuongTruService nhanKhauThuongTruService;


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "congan/welcome";
    }


    @RequestMapping(value = "/hoso",method = RequestMethod.GET)
    public String hoso(Model model) {
        return "congan/hoso";
    }

    @RequestMapping(value = "/hoso/them-nhankhau",method = RequestMethod.GET)
    public String addNhanKhau(Model model, @RequestParam(value = "soHK", required = false) Integer soHK) {
        log.info("" + soHK);
        model.addAttribute("nhanKhauThuongTru", new NhanKhauThuongTru());
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("tonGiaos", tonGiaoService.findAll());
        model.addAttribute("hocVans", hocVanService.findAll());
        model.addAttribute("quanHes", quanHeService.findAll());
        model.addAttribute("soHK", soHK);
        return "congan/nhankhau-thuongtru";
    }

    @RequestMapping(value="/quanly", method = RequestMethod.GET)
    public String quanly(Model model) {
        List<NhanKhauThuongTru> nhanKhauThuongTruList = nhanKhauThuongTruService.findAllByTrangThai(1);
        model.addAttribute("nhanKhauThuongKhauTru", nhanKhauThuongTruList);
        return "congan/quanly";
    }

    @RequestMapping(value="/danh-sach-chi-tiet-nhan-khau-thuong-tru", method = RequestMethod.GET)
    public String danhsachchitietnhankhauthuongtru(Model model, @RequestParam(value = "soHK", required = false) Integer soHK) {
        List<NhanKhauThuongTru> nhanKhauThuongTrus = nhanKhauThuongTruService.findAllBySoHKAndTrangThai(soHK, 1);

        model.addAttribute("nhanKhauThuongKhauTru", nhanKhauThuongTrus);
        return "congan/list-nk-thuong-tru";
    }
}
