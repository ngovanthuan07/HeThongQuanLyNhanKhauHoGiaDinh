package com.vanthuandev.doanphanmem.controllers.user;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import com.vanthuandev.doanphanmem.service.NguoiDungService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/nguoidung/**")
public class UserController {

    @Autowired
    private LoaiHoSoService loaiHoSoService;

    @Autowired
    private DangKyHoSoService dangKyHoSoService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "nguoidung/welcome";
    }

    @RequestMapping(value = "/dangkyhoso", method = RequestMethod.GET)
    public String dangKiHoSo(Model model) {
        model.addAttribute("loaiHoSo", loaiHoSoService.findAll());
        return "nguoidung/dangkyhoso";
    }

    @RequestMapping(value = "/trangcanhan", method = RequestMethod.GET)
    public String trangCaNhan() {
        return "nguoidung/trangcanhan";
    }
}
