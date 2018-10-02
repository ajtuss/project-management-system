package com.mycompany.controllers;

import com.mycompany.dto.AgreementDTO;
import com.mycompany.dto.SystemDTO;
import com.mycompany.services.AgreementService;
import com.mycompany.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/agreements")
public class AgreementController {

    private final AgreementService agreementService;

    private final SystemService systemService;

    @Autowired
    public AgreementController(AgreementService agreementService, SystemService systemService) {
        this.agreementService = agreementService;
        this.systemService = systemService;
    }

    @GetMapping("/active")
    public String activePage(ModelMap model) {
        return "active";
    }

    @GetMapping
    public String allPage(ModelMap model) {
        List<AgreementDTO> agreements = agreementService.getAll();
        model.addAttribute("agreements", agreements);
        return "all";
    }

    @GetMapping("/add")
    public String agreementAddPage(ModelMap model){
        List<SystemDTO> systems = systemService.getAll();
        model.addAttribute("systems", systems);
        model.addAttribute("agreement", new AgreementDTO());
        return "addAgreement";
    }

    @PostMapping("/add")
    public String addAgreement(@ModelAttribute AgreementDTO agreement){
        System.out.println(agreement);
        agreementService.save(agreement);
        return "redirect:/agreements";
    }
}
