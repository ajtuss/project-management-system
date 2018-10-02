package com.mycompany.controllers;

import com.mycompany.dto.SystemDTO;
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
@RequestMapping("/systems")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping
    public String systemsPage(ModelMap model) {
        List<SystemDTO> systems = systemService.getAll();
        model.addAttribute("systems", systems);
        return "systems";
    }

    @GetMapping("/add")
    public String systemAddPage(ModelMap model){
        model.addAttribute("system", new SystemDTO());
        return "addSystem";
    }

    @PostMapping("/add")
    public String addSystem(@ModelAttribute SystemDTO system){
        systemService.saveSystem(system);
        return "redirect:/systems";
    }

}
