package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.service.AdService;
import ru.job4j.cars.service.CarService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Set;

@Controller
public class AdController {

    private final AdService adService;
    private final CarService carService;
    public AdController(AdService adService, CarService carService) {
        this.adService = adService;
        this.carService = carService;
    }

    private Driver getUser(HttpSession session) {
        Driver user = (Driver) session.getAttribute("user");
        if (user == null) {
            user = new Driver();
            user.setLogin("Гость");
        }
        return user;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("ads", adService.getAllAds());
        model.addAttribute("user", getUser(session));
        return "index";
    }

    @GetMapping("/add")
    public String addTask(Model model, HttpSession session) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("user", getUser(session));
        return "add";
    }
/**
    @PostMapping("/addTask")
    public String newTask(@ModelAttribute Item item, HttpSession session, @RequestParam(name = "catId", required = false) Set<String> catSet) {
        item.setCreated(new Date());
        item.setUser((Account) session.getAttribute("acc"));
        if (catSet != null) {
            catSet.forEach(x -> item.getCategories().add(itemService.getCategoryById(x)));
        }
        itemService.create(item);
        return "redirect:/all";
    }
    **/
}
