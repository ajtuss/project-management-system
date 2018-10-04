package com.mycompany.controllers;

import com.mycompany.dto.AgreementDTO;
import com.mycompany.dto.SystemDTO;
import com.mycompany.services.AgreementService;
import com.mycompany.services.SystemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/agreements")
public class AgreementController {

    private static final Logger logger = Logger.getLogger(AgreementController.class);

    private final AgreementService agreementService;
    private final SystemService systemService;

    @Autowired
    public AgreementController(AgreementService agreementService, SystemService systemService) {
        this.agreementService = agreementService;
        this.systemService = systemService;
    }

    @GetMapping("/active")
    public String activePage(ModelMap model) {
        List<AgreementDTO> agreements = agreementService.getAllActive();
        model.addAttribute("agreements", agreements);
        return "activeAgreements";
    }

    @GetMapping
    public String allPage(ModelMap model) {
        List<AgreementDTO> agreements = agreementService.getAll();
        model.addAttribute("agreements", agreements);
        return "allAgreements";
    }

    @GetMapping("/add")
    public String agreementAddPage(ModelMap model) {
        List<SystemDTO> systems = systemService.getAll();
        model.addAttribute("systems", systems);
        model.addAttribute("agreement", new AgreementDTO());
        return "addAgreement";
    }

    @PostMapping("/add")
    public String addAgreement(@ModelAttribute("agreement") @Valid AgreementDTO agreement, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            List<SystemDTO> systems = systemService.getAll();
            model.addAttribute("systems", systems);
            return "addAgreement";
        }
        agreementService.save(agreement);
        return "redirect:/agreements";
    }

    @GetMapping("/edit")
    public String agreementEditPage(@RequestParam Long id, ModelMap model) {
        AgreementDTO agreement = agreementService.findById(id);
        model.addAttribute("agreement", agreement);
        List<SystemDTO> systems = systemService.getAll();
        model.addAttribute("systems", systems);
        return "editAgreement";
    }

    @PostMapping("/edit")
    public String editAgreement(@ModelAttribute("agreement") @Valid AgreementDTO agreement, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            List<SystemDTO> systems = systemService.getAll();
            model.addAttribute("systems", systems);
            return "editAgreement";
        }
        agreementService.save(agreement);
        return "redirect:/agreements";
    }


}
