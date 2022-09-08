package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdController {


    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("ads", List.of());
        return "index";
    }
}
