package com.mycompany.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agreements")
public class AgreementController {

    @GetMapping("/active")
    public String activePage(ModelMap model) {
        return "active";
    }

    @GetMapping
    public String allPage(ModelMap model) {
        return "all";
    }
}
