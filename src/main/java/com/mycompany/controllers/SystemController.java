package com.mycompany.controllers;

import com.mycompany.dto.SystemDTO;
import com.mycompany.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String addSystem(@ModelAttribute("system") @Valid SystemDTO system, BindingResult result){
        if(result.hasErrors()){
            return "addSystem";
        }
        systemService.save(system);
        return "redirect:/systems";
    }

    @GetMapping("/edit")
    public String systemEditPage(@RequestParam Long id,  ModelMap model){
        SystemDTO systemDTO = systemService.findById(id);
        model.addAttribute("system", systemDTO);
        return "editSystem";
    }

    @PostMapping("/edit")
    public String editSystem(@ModelAttribute("system") @Valid SystemDTO system, BindingResult result){
        if(result.hasErrors()){
            return "editSystem";
        }
        systemService.update(system);
        return "redirect:/systems";
    }
}
