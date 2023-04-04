package com.example.webshop.controllers;

import com.example.webshop.models.Brand;
import com.example.webshop.models.Fruit;
import com.example.webshop.models.GardenProduct;
import com.example.webshop.models.Vegetable;
import com.example.webshop.services.BrandsServices;
import com.example.webshop.services.FruitsService;
import com.example.webshop.services.GardenProductsService;
import com.example.webshop.services.VegetableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final GardenProductsService gardenProductsService;
    private final FruitsService fruitsService;
    private final VegetableService vegetableService;
    private final BrandsServices brandsServices;

    public HomeController(GardenProductsService gardenProductsService, FruitsService fruitsService, VegetableService vegetableService, BrandsServices brandsServices) {
        this.gardenProductsService = gardenProductsService;
        this.fruitsService = fruitsService;
        this.vegetableService = vegetableService;
        this.brandsServices = brandsServices;
    }

    @GetMapping("/products")
    public String findAll(Model model) {
        List<GardenProduct> products = new ArrayList<>(fruitsService.getAllFruits());
        products.addAll(vegetableService.getAllVegetables());
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/fruit-create")
    public String createFruitForm(Fruit fruit, Model model) {
        model.addAttribute("fruit", fruit);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        return "fruit-create";
    }

    @PostMapping("/fruit-create")
    public String createFruit(Fruit fruit, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        fruit.setBrand(brand);
        gardenProductsService.saveFruit(fruit);
        return "redirect:/products";
    }

    @GetMapping("/vegetable-create")
    public String createVegetableForm(Vegetable vegetable, Model model) {
        model.addAttribute("vegetable", vegetable);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        return "vegetable-create";
    }

    @PostMapping("/vegetable-create")
    public String createVegetable(Vegetable vegetable, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        vegetable.setBrand(brand);
        gardenProductsService.saveVegetable(vegetable);
        return "redirect:/products";
    }

    @GetMapping("product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        gardenProductsService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/fruit-update/{id}")
    public String updateFruitForm(@PathVariable("id") Long id, Model model) {
        Fruit fruit = fruitsService.getFruitById(id);
        model.addAttribute("fruit", fruit);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        return "fruit-update";
    }

    @PostMapping("/fruit-update")
    public String updateFruit(Fruit fruit, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        fruit.setBrand(brand);
        fruitsService.saveFruit(fruit);
        return "redirect:/products";
    }

    @GetMapping("/vegetable-update/{id}")
    public String updateVegetableForm(@PathVariable("id") Long id, Model model) {
        Vegetable vegetable = vegetableService.getVegetableById(id);
        model.addAttribute("vegetable", vegetable);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        return "vegetable-update";
    }

    @PostMapping("/vegetable-update")
    public String updateVegetable(Vegetable vegetable, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        vegetable.setBrand(brand);
        vegetableService.saveVegetable(vegetable);
        return "redirect:/products";
    }
}
