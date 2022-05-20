package com.vanthuandev.doanphanmem.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/nguoidung/**")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "nguoidung/index";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("HelloWorldj");
    }

    @RequestMapping(value = "dangkycapsohokhau",method = RequestMethod.GET)
    public String dangkycapsohokhau(Model model) {
        return "nguoidung/dangkycapsohokhau";
    }

}
