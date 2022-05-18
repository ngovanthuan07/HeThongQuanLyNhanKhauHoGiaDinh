package com.vanthuandev.doanphanmem.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/nguoidung**")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "nguoidung/index";
    }
}
