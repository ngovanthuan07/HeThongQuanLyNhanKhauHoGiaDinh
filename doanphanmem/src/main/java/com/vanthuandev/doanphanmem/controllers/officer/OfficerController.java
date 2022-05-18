package com.vanthuandev.doanphanmem.controllers.officer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/canbo**")
public class OfficerController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "canbo/index";
    }
}
