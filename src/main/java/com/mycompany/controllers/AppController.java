package com.mycompany.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class AppController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        return "home";
    }

    @RequestMapping(value = {"/contactus"}, method = RequestMethod.GET)
    public String contactUsPage(ModelMap model) {
        return "contactus";
    }

    @PostMapping(value = "/upload")
    public String submit(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        ra.addFlashAttribute("message",  "message");
        System.out.println(file.getOriginalFilename() + file.getContentType());
        return "redirect:/import";
    }

    @GetMapping("/import")
    public String importPage(){
        return "import";
    }

}
