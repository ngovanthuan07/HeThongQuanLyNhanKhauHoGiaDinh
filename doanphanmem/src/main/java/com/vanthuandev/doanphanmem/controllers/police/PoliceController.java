package com.vanthuandev.doanphanmem.controllers.police;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/congan**")
public class PoliceController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "congan/index";
    }
}
