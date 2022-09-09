package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.service.AdService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdController {

    private final AdService adService;
    public AdController(AdService adService) {
        this.adService = adService;
    }


    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("ads", adService.getAllAds());
        return "index";
    }
}
