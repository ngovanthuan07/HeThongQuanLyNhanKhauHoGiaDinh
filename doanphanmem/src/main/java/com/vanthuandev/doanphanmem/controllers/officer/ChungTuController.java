package com.vanthuandev.doanphanmem.controllers.officer;

import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;
import com.vanthuandev.doanphanmem.pojos.GiayChungTu;
import com.vanthuandev.doanphanmem.pojos.GiayKhaiSinh;
import com.vanthuandev.doanphanmem.service.DanTocService;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import com.vanthuandev.doanphanmem.service.GiayChungTuService;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/canbo/chungtu**")
public class ChungTuController {
    @Autowired
    private DanTocService danTocService;

    @Autowired
    private GiayChungTuService giayChungTuService;

    @Autowired
    private LoaiHoSoService loaiHoSoService;

    @Autowired
    private DangKyHoSoService dangKyHoSoService;

    @GetMapping("/capgiaychungtu")
    public String capGiayChungTu(Model model) {
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("giayChungTu", new GiayChungTu());
        return "canbo/chungtu/capgiaychungtu";
    }

    @PostMapping("/capgiaychungtu")
    public ResponseEntity<String> capGiayChungTu(Model model, @ModelAttribute GiayChungTu giayChungTu) {
        if(giayChungTu != null) {
            giayChungTuService.save(giayChungTu);
        }
        return new ResponseEntity<>("Oke", HttpStatus.OK);
    }
    // -------------------- danh sach chung tu --------------------
    @GetMapping("/danhsachchungtu")
    public String danhSachChungTu(Model model) {
        model.addAttribute("listChungTu", giayChungTuService.findAll());
        return "canbo/chungtu/danhsachchungtu";
    }

    @GetMapping("/danhsachchungtu/chinhsua")
    public String chinhSuaChungTu(Model model,
                                  @RequestParam(value = "maCT", required = false) Integer maCT) {
        GiayChungTu giayChungTu = giayChungTuService.findById(maCT).get();
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("giayChungTu", giayChungTu);
        return "canbo/chungtu/chinhsua-giaychungtu";
    }

    @PostMapping("/danhsachchungtu/chinhsua")
    public String chinhSuaChungTu(Model model, @ModelAttribute GiayChungTu giayChungTu) {
        GiayChungTu giayChungTu1 = giayChungTuService.save(giayChungTu);
        return "redirect:/canbo/chungtu/danhsachchungtu/chinhsua?maCT=" + giayChungTu1.getMaCT();
    }

    @GetMapping("/danhsachchungtu/xoa")
    public String xoaChungTu(Model model,
                             @RequestParam(value = "maCT", required = false) Integer maCT) {
        giayChungTuService.deleteById(maCT);
        return "redirect:/canbo/chungtu/danhsachchungtu";
    }

    // -------------------- danh sach dang ky chung tu --------------------
    @GetMapping("/danhsachdangkychungtu")
    public String danhSachDangKyKhaiSinh(Model model) {
        List<DangKyHoSo> dangKyHoSos = dangKyHoSoService.findAllByMaLHSAndTrangThai(7, 1);
        if(!dangKyHoSos.isEmpty()) {
            model.addAttribute("dangKyHoSos", dangKyHoSos);
            return "canbo/chungtu/loaihoso";
        }
        return "redirect:/canbo";
    }

    @GetMapping("/danhsachdangkychungtu/xem")
    public String xemHoSo(Model model,
                          @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            model.addAttribute("dangKHS", dkhsOptional.get());
            model.addAttribute("loaiHoSo", loaiHoSoService.findAll());
            return "canbo/chungtu/chitietdangki";
        }
        return "redirect:/canbo";
    }
    @GetMapping("/danhsachdangkychungtu/duyet")
    public String duyetHoSo(Model model,
                            @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            DangKyHoSo dangKyHoSo = dkhsOptional.get();
            dangKyHoSo.setTrangThai(2);
            dangKyHoSoService.save(dangKyHoSo);
        }
        return "redirect:/canbo/chungtu/danhsachdangkychungtu";
    }

    @GetMapping("/danhsachdangkychungtu/tuchoi")
    public String tuChoiHoSo(Model model,
                             @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            DangKyHoSo dangKyHoSo = dkhsOptional.get();
            dangKyHoSo.setTrangThai(0);
            dangKyHoSoService.save(dangKyHoSo);
        }
        return "redirect:/canbo/chungtu/danhsachdangkychungtu";
    }

}
