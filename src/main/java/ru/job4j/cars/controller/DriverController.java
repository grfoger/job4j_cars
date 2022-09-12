package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.service.AdService;
import ru.job4j.cars.service.DriverService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class DriverController {

    private final DriverService driverService;
    private final AdService adService;

    public DriverController(DriverService driverService, AdService adService) {
        this.driverService = driverService;
        this.adService = adService;
    }

    private Driver getUser(HttpSession session) {
        Driver user = (Driver) session.getAttribute("user");
        if (user == null) {
            user = new Driver();
            user.setLogin("Гость");
        }
        return user;
    }

    @GetMapping("/reg")
    public String formRegistration(Model model, @RequestParam(name = "fail", required = false) Boolean fail, HttpSession session) {
        model.addAttribute("user", getUser(session));
        model.addAttribute("fail", fail != null);
        return "reg";
    }

    @PostMapping("/reg")
    public String registration(@ModelAttribute Driver user, HttpSession session) {
        Optional<Driver> regUser = driverService.add(user);
        if (regUser.isEmpty()) {
            return "redirect:/reg?fail=true";
        }
        session.setAttribute("user", regUser.get());
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success(Model model, HttpSession session) {
        model.addAttribute("user", getUser(session));
        model.addAttribute("ads", adService.getAllAds());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail, HttpSession session) {
        model.addAttribute("fail", fail != null);
        model.addAttribute("user", getUser(session));
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Driver user, HttpSession session) {
        Optional<Driver> userDb = driverService.findUserByLoginAndPass(
                user.getLogin(), user.getPassword()
        );
        if (userDb.isEmpty()) {
            return "redirect:/login?fail=true";
        }
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
