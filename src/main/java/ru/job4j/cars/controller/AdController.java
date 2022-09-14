package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.service.AdService;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.DriverService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class AdController {

    private final AdService adService;
    private final CarService carService;
    private final DriverService driverService;
    public AdController(AdService adService, CarService carService, DriverService driverService) {
        this.adService = adService;
        this.carService = carService;
        this.driverService = driverService;
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

    @PostMapping("/add")
    public String newTask(Model model,
                          @ModelAttribute Ad ad, HttpSession session,
                          @RequestParam(name = "carId", required = false) int carId,
                          @RequestParam(name = "driverId", required = false) int driverId,
                          @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        ad.setCreated(LocalDateTime.now());
        ad.setDriver(driverService.getDriverByID(driverId));
        ad.setCar(carService.getCarById(carId));
        ad.setPhoto(file.getBytes());
        adService.add(ad);
        model.addAttribute("ads", adService.getAllAds());
        model.addAttribute("user", getUser(session));

        return "redirect:/index";
    }

    @GetMapping("/photoAd/{adId}")
    public ResponseEntity<Resource> download(@PathVariable("adId") int id) {
        Ad ad = adService.findById(id);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(ad.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(ad.getPhoto()));
    }

    @GetMapping("/setSold/{adId}")
    public String setSold(Model model, HttpSession session, @PathVariable("adId") int id) {
        Ad ad = adService.findById(id);
        ad.setSold(true);
        adService.update(ad);
        model.addAttribute("ads", adService.getAllAds());
        model.addAttribute("user", getUser(session));
        return "index";
    }

}
