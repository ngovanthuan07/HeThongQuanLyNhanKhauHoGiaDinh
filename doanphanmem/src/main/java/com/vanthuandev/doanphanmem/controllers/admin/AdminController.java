package com.vanthuandev.doanphanmem.controllers.admin;

import com.vanthuandev.doanphanmem.configs.PasswordConfig;
import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import com.vanthuandev.doanphanmem.service.NguoiDungService;
import com.vanthuandev.doanphanmem.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
//        return "admin/index";
        return "redirect:/admin/danhsachtaikhoan/searchpaginated?username=&size=5&page=1";
    }


    @RequestMapping(value = "/captaikhoan", method = RequestMethod.GET)
    public String captaikhoan(Model model, HttpServletRequest request, HttpServletResponse response) {
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
        if (result.hasErrors()) {
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
    public String danhsachtaikhoan() {
        return "redirect:/admin/danhsachtaikhoan/searchpaginated?username=&size=5&page=1";
    }

    @RequestMapping(value = "/danhsachtaikhoan/searchpaginated", method = RequestMethod.GET)
    public String search(Model model,
                         @RequestParam(name = "username", required = false) String username,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        model.addAttribute("currentPage", currentPage);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("maNguoiDung"));
        Page<NguoiDung> resultPage = null;

        if (StringUtils.hasText(username)) {
            resultPage = nguoiDungService.findNguoiDungByUsernameAndTrangThaiContaining(username, 1,pageable);
            model.addAttribute("username", username);
        } else {
            resultPage = nguoiDungService.findNguoiDungByTrangThaiContaining(1,pageable);
        }
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int starts = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) {
                    starts = end - 5;
                } else {
                    if (starts == 1) {
                        end = starts + 5;
                    }
                }
            }
           List<Integer> pageNumbers = IntStream.rangeClosed(starts, end)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("nguoiDungPage", resultPage);
        return "admin/danh_sach_tai_khoan";
    }


    @RequestMapping(value = "/khoamotaikhoan", method = RequestMethod.GET)
    public String khoataikhoan(Model model,
                               @RequestParam(value = "id", required = false) Integer id) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);

        if (nguoiDung != null) {
            int trangThai = nguoiDung.getTrangThai() == 1 ? 0 : 1;
            nguoiDung.setTrangThai(trangThai);
            nguoiDungService.updateNguoiDung(nguoiDung, "updateStatus");
        }
        return "redirect:/admin/danhsachtaikhoan";
    }



    @RequestMapping(value = "/danhsachtaikhoanbikhoa", method = RequestMethod.GET)
    public String danhsachtaikhoanbikhoa() {
        return "redirect:/admin/danhsachtaikhoanbikhoa/searchpaginated?username=&size=5&page=1";
    }

    @RequestMapping(value = "/danhsachtaikhoanbikhoa/searchpaginated", method = RequestMethod.GET)
    public String search_block(Model model,
                         @RequestParam(name = "username", required = false) String username,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        model.addAttribute("currentPage", currentPage);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("maNguoiDung"));
        Page<NguoiDung> resultPage = null;

        if (StringUtils.hasText(username)) {
            resultPage = nguoiDungService.findNguoiDungByUsernameAndTrangThaiContaining(username, 0,pageable);
            model.addAttribute("username", username);
        } else {
            resultPage = nguoiDungService.findNguoiDungByTrangThaiContaining(0,pageable);
        }
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int starts = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) {
                    starts = end - 5;
                } else {
                    if (starts == 1) {
                        end = starts + 5;
                    }
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(starts, end)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("nguoiDungPage", resultPage);
        return "admin/danh_sach_tai_khoan_bi_khoa";
    }
}
