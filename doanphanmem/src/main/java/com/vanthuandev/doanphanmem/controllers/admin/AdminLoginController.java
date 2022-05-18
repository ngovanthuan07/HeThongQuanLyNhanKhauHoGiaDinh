package com.vanthuandev.doanphanmem.controllers.admin;

import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin-panel")
public class AdminLoginController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/admin-panel/login";
    }
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,

            ModelMap modelMap)
    {
        if(error != null) {
            modelMap.put("msg", "Tên đăng nhập hoặc tài khoản không hợp lệ");
        }
        if(logout != null) {
            modelMap.put("msg", "Đăng xuất thành công");
        }

        modelMap.put("formLogin", "Admin Login");
        modelMap.put("user", new NguoiDung());
        return  "admin/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("currentUser");
        return "redirect:/admin-panel/login?logout";
    }

    @RequestMapping(value = "accessDenied", method = RequestMethod.GET)
    public String accessDenied() {
        return "admin/accessDenied";
    }

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String welcome() {
       return "admin/welcome";
    }
}
