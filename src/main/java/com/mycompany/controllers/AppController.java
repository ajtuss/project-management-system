package com.mycompany.controllers;

import com.mycompany.domain.ImportMessage;
import com.mycompany.services.ImportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AppController {

    private final ImportFileService fileService;

    @Autowired
    public AppController(ImportFileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/contactus")
    public String contactUsPage() {
        return "contactus";
    }

    @PostMapping(value = "/import")
    public String submit(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        ImportMessage importMessage = fileService.importSpreadsheet(file);
        ra.addFlashAttribute("message", importMessage);
        return "redirect:/import";
    }

    @GetMapping("/import")
    public String importPage() {
        return "import";
    }

}
