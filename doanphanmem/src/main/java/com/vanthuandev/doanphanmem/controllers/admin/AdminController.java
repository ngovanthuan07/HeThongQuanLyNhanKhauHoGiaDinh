package com.vanthuandev.doanphanmem.controllers.admin;

import com.vanthuandev.doanphanmem.configs.PasswordConfig;
import com.vanthuandev.doanphanmem.pojos.NguoiDung;

import com.vanthuandev.doanphanmem.service.NguoiDungService;
import com.vanthuandev.doanphanmem.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/**")
public class AdminController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private VaiTroService vaiTroService;

    @Autowired
    private NguoiDungService nguoiDungService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "admin/index";
    }


    @RequestMapping(value = "/captaikhoan", method = RequestMethod.GET)
    public String captaikhoan(Model model) {
        model.addAttribute("option", "edit");
        model.addAttribute("nguoidung", new NguoiDung());
        model.addAttribute("vaitros", vaiTroService.findAll());
        return "admin/cap_tai_khoan";
    }

    @RequestMapping(value = "/captaikhoan", method = RequestMethod.POST)
    public String captaikhoan(ModelMap modelMap, @ModelAttribute(value = "nguoidung") NguoiDung nguoiDung) {
        String msg = nguoiDungService.addNguoiDung(nguoiDung);
        if (msg.contains("đã tồn tại")) {
            modelMap.put("msg", "Người dùng đã tồn tại trong hệ thống");
        }
        if (msg.contains("thành công")) {
            modelMap.put("msgSuccess", "Cấp tài khoản thành công");
        }
        if (msg.contains("Lỗi")) {
            modelMap.put("msgError", "Lỗi hệ thống");
        }
        modelMap.addAttribute("option", "update");
        modelMap.addAttribute("nguoidung", nguoiDung);
        modelMap.addAttribute("vaitros", vaiTroService.findAll());
        return "admin/cap_tai_khoan";
    }

    @RequestMapping(value = "/capnhattaikhoan", method = RequestMethod.GET)
    public String capnhattaikhoan(Model model,
                                  @RequestParam(value = "id", required = false) Integer Id) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(Id);
        model.addAttribute("option", "update");
        model.addAttribute("nguoidung", nguoiDung);
        model.addAttribute("vaitros", vaiTroService.findAll());

        return "admin/cap_tai_khoan";
    }

    @RequestMapping(value = "/capnhattaikhoan", method = RequestMethod.POST)
    public String capnhattaikhoan(ModelMap modelMap,
                                  @ModelAttribute(value = "nguoidung") @Valid NguoiDung nguoiDung,
                                  BindingResult result) {
        if(result.hasErrors())
        {
            modelMap.addAttribute("option", "update");
            modelMap.addAttribute("nguoidung", nguoiDung);
            modelMap.addAttribute("vaitros", vaiTroService.findAll());
            return "admin/cap_tai_khoan";
        }
        String msg = nguoiDungService.updateNguoiDung(nguoiDung, "");
        if (msg.contains("không tồn tại")) {
            modelMap.put("msg", "Người dùng không tồn tại trong hệ thống");
        }
        if (msg.contains("thành công")) {
            modelMap.put("msgSuccess", "Cập nhật tài khoản thành công");
        }
        if (msg.contains("Lỗi")) {
            modelMap.put("msgError", "Lỗi hệ thống");
        }
        modelMap.addAttribute("option", "update");
        modelMap.addAttribute("nguoidung", nguoiDung);
        modelMap.addAttribute("vaitros", vaiTroService.findAll());
        return "admin/cap_tai_khoan";
    }


    @RequestMapping(value = "/danhsachtaikhoan", method = RequestMethod.GET)
    public String danhsachtaikhoan(Model model) {
        model.addAttribute("nguoidungs", nguoiDungService.findAll());
        return "admin/danh_sach_tai_khoan";
    }

    @RequestMapping(value = "/khoamotaikhoan", method = RequestMethod.GET)
    public String khoataikhoan(Model model,
                               @RequestParam(value = "id", required = false) Integer id) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);

        if(nguoiDung != null)
        {
            int trangThai = nguoiDung.getTrangThai() == 1 ? 0 : 1;
            nguoiDung.setTrangThai(trangThai);
            nguoiDungService.updateNguoiDung(nguoiDung, "updateStatus");
        }
        return "redirect:/admin/danhsachtaikhoan";
    }
}
