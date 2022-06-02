package com.vanthuandev.doanphanmem.controllers.police;

import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;
import com.vanthuandev.doanphanmem.pojos.LoaiHoSo;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/congan/bieumau**")
public class BieuMauController {
    @Autowired
    private LoaiHoSoService loaiHoSoService;

    @Autowired
    private DangKyHoSoService dangKyHoSoService;

    @GetMapping("/ho-so")
    public String danhSachDangKyThuongTru(Model model,
                                          @RequestParam(value = "maLHS", required = false) Integer maLHS) {
        List<DangKyHoSo> dangKyHoSos = dangKyHoSoService.findAllByMaLHSAndTrangThai(maLHS, 1);
        if(!dangKyHoSos.isEmpty()) {
            model.addAttribute("dangKyHoSos", dangKyHoSos);
            return "congan/hoso/loaihoso";
        }
        return "congan/empty";
    }
    @GetMapping("/ho-so/xem")
    public String xemHoSo(Model model,
                          @RequestParam(value = "maLHS", required = false) Integer maLHS,
                          @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            model.addAttribute("dangKHS", dkhsOptional.get());
            model.addAttribute("loaiHoSo", loaiHoSoService.findAll());
            return "congan/hoso/chitietdangki";
        }
        return "congan/empty";
    }

    @GetMapping("/ho-so/tuchoi")
    public String tuChoiHoSo(Model model,
                             @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            DangKyHoSo dangKyHoSo = dkhsOptional.get();
            dangKyHoSo.setTrangThai(0);
            dangKyHoSoService.save(dangKyHoSo);
        }
        return "redirect:/congan/bieumau/ho-so?maLHS= " + dkhsOptional.get().getLoaiHoSo().getMaLHS();
    }

    @GetMapping("/ho-so/duyet")
    public String duyetHoSo(Model model,
                             @RequestParam(value="maHS", required = false) Integer maHS) {
        Optional<DangKyHoSo> dkhsOptional = dangKyHoSoService.findById(maHS);
        if(dkhsOptional.isPresent()) {
            DangKyHoSo dangKyHoSo = dkhsOptional.get();
            dangKyHoSo.setTrangThai(2);
            dangKyHoSoService.save(dangKyHoSo);
        }
        return "redirect:/congan/bieumau/ho-so?maLHS= " + dkhsOptional.get().getLoaiHoSo().getMaLHS();
    }

}
