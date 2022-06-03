package com.vanthuandev.doanphanmem.controllers.user;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import com.vanthuandev.doanphanmem.service.NguoiDungService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/nguoidung/**")
public class UserController {

    @Autowired
    private LoaiHoSoService loaiHoSoService;

    @Autowired
    private DangKyHoSoService dangKyHoSoService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public String trangCaNhan(Model model, HttpSession session) {
        NguoiDung nguoiDungSession = (NguoiDung) session.getAttribute("currentUser");

        NguoiDung newND = nguoiDungService.getNguoiDungById(nguoiDungSession.getMaNguoiDung());

        model.addAttribute("nguoiDung", newND);
        return "nguoidung/trangcanhan";
    }

    @RequestMapping(value= "/trangcanhan/capnhat", method = RequestMethod.POST)
    public String trangCaNhan(Model model, HttpSession session,@ModelAttribute NguoiDung nguoiDung) {
        NguoiDung nguoiDungSession = (NguoiDung) session.getAttribute("currentUser");
        nguoiDung.setUsername(nguoiDungSession.getUsername());
        nguoiDung.setPassword(nguoiDungSession.getPassword());
        nguoiDung.setImage(nguoiDungSession.getImage());
        nguoiDung.setPublicId(nguoiDungSession.getPublicId());
        nguoiDung.setTrangThai(1);
        nguoiDung.setVaiTros(nguoiDungSession.getVaiTros());
        NguoiDung newND = nguoiDungService.updateUser(nguoiDung);
        if(newND != null) {
            session.setAttribute("currentUser", newND);
            return "redirect:/nguoidung/trangcanhan";
        }
        return "extras/_403";
    }

    // thay doi mat khau
    @RequestMapping(value = "/trangcanhan/thaydoimatkhau", method = RequestMethod.GET)
    public String thayDoiMatKhau(Model model, HttpSession session) {
        NguoiDung nguoiDungSession = (NguoiDung) session.getAttribute("currentUser");
        NguoiDung newND = nguoiDungService.getNguoiDungById(nguoiDungSession.getMaNguoiDung());
        model.addAttribute("nguoiDung", newND);
        return "nguoidung/thaydoimatkhau";
    }

    @RequestMapping(value = "/trangcanhan/thaydoimatkhau", method = RequestMethod.POST)
    public String thayDoiMatKhau(Model model, HttpSession session,
                                 @RequestParam("matKhauCu") String matKhauCu,
                                 @RequestParam("matKhauMoi") String matKhauMoi,
                                 @RequestParam("xacNhanMatKhau") String xacNhanMatKhau) {
        NguoiDung nguoiDungSession = (NguoiDung) session.getAttribute("currentUser");
        boolean isCheck = true;
        if(!passwordEncoder.matches(matKhauCu, nguoiDungSession.getPassword())) {
            model.addAttribute("matKhauCu", true);
            isCheck = false;
        }
        if(!matKhauMoi.equals(xacNhanMatKhau)) {
            model.addAttribute("xacNhanMatKhau", true);
            isCheck = false;
        }
        if(matKhauMoi.equals("")) {
            model.addAttribute("matKhauMoi", true);
            model.addAttribute("message", "Vui lòng không được để trống");
            isCheck = false;
        }
        if(matKhauMoi.length() < 6) {
            model.addAttribute("matKhauMoi", true);
            model.addAttribute("message", "Mật khẩu phải lớn hơn 6 ký tự");
            isCheck = false;
        }
        if(!isCheck) {
            return "nguoidung/thaydoimatkhau";
        }
        nguoiDungSession.setPassword(passwordEncoder.encode(matKhauMoi));
        NguoiDung nguoiDung = nguoiDungService.updateUser(nguoiDungSession);
        session.setAttribute("currentUser", nguoiDung);
        return "redirect:/nguoidung/trangcanhan";
    }


}
