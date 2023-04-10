package com.example.webshop.controllers;

import com.example.webshop.models.models.Brand;
import com.example.webshop.models.models.Fruit;
import com.example.webshop.models.models.GardenProduct;
import com.example.webshop.models.models.Vegetable;
import com.example.webshop.services.BrandsServices;
import com.example.webshop.services.FruitsService;
import com.example.webshop.services.GardenProductsServiceImpl;
import com.example.webshop.services.VegetableService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CustomerController {
    private final GardenProductsServiceImpl gardenProductsService;
    private final FruitsService fruitsService;
    private final VegetableService vegetableService;
    private final BrandsServices brandsServices;

    public CustomerController(GardenProductsServiceImpl gardenProductsService, FruitsService fruitsService, VegetableService vegetableService, BrandsServices brandsServices) {
        this.gardenProductsService = gardenProductsService;
        this.fruitsService = fruitsService;
        this.vegetableService = vegetableService;
        this.brandsServices = brandsServices;
    }

    @GetMapping("/catalog")
    public String findAll(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isNotAuthorized = authentication == null || authentication.getPrincipal().equals("anonymousUser");
        model.addAttribute("isAuthorized", !isNotAuthorized);

        List<GardenProduct> products = new ArrayList<>(fruitsService.getAllFruits());
        products.addAll(vegetableService.getAllVegetables());
        model.addAttribute("products", products);
        return "customer/customer-product-list";
    }
}

