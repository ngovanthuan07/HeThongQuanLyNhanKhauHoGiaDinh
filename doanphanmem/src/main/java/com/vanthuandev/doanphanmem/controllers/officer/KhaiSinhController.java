package com.vanthuandev.doanphanmem.controllers.officer;

import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;
import com.vanthuandev.doanphanmem.pojos.GiayKhaiSinh;
import com.vanthuandev.doanphanmem.pojos.LoaiHoSo;
import com.vanthuandev.doanphanmem.service.DanTocService;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import com.vanthuandev.doanphanmem.service.GiayKhaiSinhService;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/canbo/khaisinh**")
public class KhaiSinhController {

    @Autowired
    private DanTocService danTocService;

    @Autowired
    private GiayKhaiSinhService giayKhaiSinhService;

    @Autowired
    private LoaiHoSoService loaiHoSoService;

    @Autowired
    private DangKyHoSoService dangKyHoSoService;

    @GetMapping("/capgiaykhaisinh")
    public String capGiayKhaiSinh(Model model) {
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("giayKhaiSinh", new GiayKhaiSinh());
        return "canbo/khaisinh/capgiaykhaisinh";
    }

    @PostMapping("/capgiaykhaisinh")
    public String capGiayKhaiSinh(Model model, @ModelAttribute GiayKhaiSinh giayKhaiSinh) {
        if(giayKhaiSinh != null) {
            giayKhaiSinh.setNgayCapNhat(new Date());
            giayKhaiSinh.setNgayDangKy(new Date());
            giayKhaiSinh.setLyDo("CAP_GIAY_KHAI_SINH");
            giayKhaiSinhService.save(giayKhaiSinh);
        }
        return "redirect:/canbo/khaisinh/capgiaykhaisinh";
    }

    @GetMapping("/danhsachkhaisinh")
    public String danhSachKhaiSinh(Model model) {
        model.addAttribute("listKhaiSinh", giayKhaiSinhService.findAll());
        return "canbo/khaisinh/danhsachkhaisinh";
    }

    @GetMapping("/danhsachkhaisinh/chinhsua")
    public String chinhSuaKhaiSinh(Model model, @RequestParam(value = "maKS", required = false) Integer maKS) {
        GiayKhaiSinh giayKhaiSinh = giayKhaiSinhService.findById(maKS).get();
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("giayKhaiSinh", giayKhaiSinh);
        return "canbo/khaisinh/chinhsua-giaykhaisinh";
    }

    @GetMapping("/danhsachkhaisinh/xoa")
    public String xoaKhaiSinh(Model model, @RequestParam(value = "maKS", required = false) Integer maKS) {
        giayKhaiSinhService.deleteById(maKS);
        return "redirect:/canbo/khaisinh/danhsachkhaisinh";
    }

    @PostMapping("/danhsachkhaisinh/chinhsua")
    public String chinhSuaKhaiSinh(Model model, @ModelAttribute GiayKhaiSinh giayKhaiSinh) {
        GiayKhaiSinh giayKhaiSinh1 = giayKhaiSinhService.save(giayKhaiSinh);
        return "redirect:/canbo/khaisinh/danhsachkhaisinh/chinhsua?maKS=" + giayKhaiSinh1.getMaKS();
    }
    // --------------- Xử lý đăng ký khai sinh ---------------
    @GetMapping("/danhsachdangkykhaisinh")
    public String danhSachDangKyKhaiSinh(Model model) {
        List<DangKyHoSo> dangKyHoSos = dangKyHoSoService.findAllByMaLHSAndTrangThai(6, 1);
        if(!dangKyHoSos.isEmpty()) {
            model.addAttribute("dangKyHoSos", dangKyHoSos);
            return "canbo/khaisinh/loaihoso";
        }
        return "redirect:/canbo";
    }

    @GetMapping("/danhsachdangkykhaisinh/xem")
    public String xemHoSo(Model model,
                          @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            model.addAttribute("dangKHS", dkhsOptional.get());
            model.addAttribute("loaiHoSo", loaiHoSoService.findAll());
            return "canbo/khaisinh/chitietdangki";
        }
        return "congan/empty";
    }
    @GetMapping("/danhsachdangkykhaisinh/duyet")
    public String duyetHoSo(Model model,
                            @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            DangKyHoSo dangKyHoSo = dkhsOptional.get();
            dangKyHoSo.setTrangThai(2);
            dangKyHoSoService.save(dangKyHoSo);
        }
        return "redirect:/canbo/khaisinh/danhsachdangkykhaisinh";
    }

    @GetMapping("/danhsachdangkykhaisinh/tuchoi")
    public String tuChoiHoSo(Model model,
                            @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            DangKyHoSo dangKyHoSo = dkhsOptional.get();
            dangKyHoSo.setTrangThai(0);
            dangKyHoSoService.save(dangKyHoSo);
        }
        return "redirect:/canbo/khaisinh/danhsachdangkykhaisinh";
    }
}
