package com.mycompany.controllers;

import com.mycompany.domain.ImportMessage;
import com.mycompany.services.ImportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class AppController {

    private final ImportFileService fileService;

    @Autowired
    public AppController(ImportFileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        return "home";
    }

    @RequestMapping(value = {"/contactus"}, method = RequestMethod.GET)
    public String contactUsPage(ModelMap model) {
        return "contactus";
    }

    @PostMapping(value = "/import")
    public String submit(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        try {
            ImportMessage importMessage = fileService.importSpreadsheet(file);
            ra.addFlashAttribute("message", importMessage);

        } catch (Exception e) {
            ra.addFlashAttribute("message", new ImportMessage("Błąd pliku. " + e.getMessage()));
        }
        return "redirect:/import";
    }

    @GetMapping("/import")
    public String importPage() {
        return "import";
    }

}
