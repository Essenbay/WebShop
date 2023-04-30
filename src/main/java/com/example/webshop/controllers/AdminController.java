package com.example.webshop.controllers;

import com.example.webshop.models.dto.UserDto;
import com.example.webshop.models.models.*;
import com.example.webshop.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Controller
public class AdminController {
    private final GardenProductsServiceImpl gardenProductsService;
    private final FruitsService fruitsService;
    private final VegetableService vegetableService;
    private final BrandsServices brandsServices;
    private final CountryServices countryServices;
    private UserService userService;

    @GetMapping("admin/products")
    public String findAll(Model model) {
        List<GardenProduct> products = new ArrayList<>(fruitsService.getAllFruits());
        products.addAll(vegetableService.getAllVegetables());
        model.addAttribute("products", products);
        return "admin/admin-product-list";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/users/remove-admin-role/{id}")
    public String removeAdminRole(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.removeAdminRole(user);
        return "redirect:/users";
    }

    @GetMapping("/users/give-admin-role/{id}")
    public String giveAdminRole(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.giveUserAdminRole(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("admin/products/fruit-create")
    public String createFruitForm(Model model) {
        GardenProduct fruit = gardenProductsService.getFactory(GardenProductTypes.FRUIT).create();
        model.addAttribute("fruit", fruit);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        List<Country> countries = countryServices.getAllCountries();
        model.addAttribute("countries", countries);
        return "admin/fruit-create";
    }

    @PostMapping("/admin/products/fruit-create")
    public String createFruit(Fruit fruit, @RequestParam(name = "brand_id") Long brandId, @RequestParam(name = "country_id") Long countryId) {
        Brand brand = brandsServices.findById(brandId);
        fruit.setBrand(brand);
        Country country = countryServices.findById(countryId);
        fruit.setCountry(country);
        fruitsService.saveFruit(fruit);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/vegetable-create")
    public String createVegetableForm(Model model) {
        GardenProduct vegetable = gardenProductsService.getFactory(GardenProductTypes.VEGETABLE).create();
        model.addAttribute("vegetable", vegetable);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        List<Country> countries = countryServices.getAllCountries();
        model.addAttribute("countries", countries);
        return "admin/vegetable-create";
    }

    @PostMapping("/admin/products/vegetable-create")
    public String createVegetable(Vegetable vegetable, @RequestParam(name = "brand_id") Long brandId, @RequestParam(name = "country_id") Long countryId) {
        Brand brand = brandsServices.findById(brandId);
        vegetable.setBrand(brand);
        Country country = countryServices.findById(countryId);
        vegetable.setCountry(country);
        vegetableService.saveVegetable(vegetable);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        gardenProductsService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/fruit-update/{id}")
    public String updateFruitForm(@PathVariable("id") Long id, Model model) {
        Fruit fruit = fruitsService.getFruitById(id);
        model.addAttribute("fruit", fruit);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        List<Country> countries = countryServices.getAllCountries();
        model.addAttribute("countries", countries);
        return "admin/fruit-update";
    }

    @PostMapping("/admin/products/fruit-update")
    public String updateFruit(Fruit fruit, @RequestParam(name = "brand_id") Long id, @RequestParam(name = "country_id") Long countryId) {
        Brand brand = brandsServices.findById(id);
        fruit.setBrand(brand);
        Country country = countryServices.findById(countryId);
        fruit.setCountry(country);
        fruitsService.saveFruit(fruit);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/vegetable-update/{id}")
    public String updateVegetableForm(@PathVariable("id") Long id, Model model) {
        Vegetable vegetable = vegetableService.getVegetableById(id);
        model.addAttribute("vegetable", vegetable);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        List<Country> countries = countryServices.getAllCountries();
        model.addAttribute("countries", countries);
        return "admin/vegetable-update";
    }

    @PostMapping("/admin/products/vegetable-update")
    public String updateVegetable(Vegetable vegetable, @RequestParam(name = "brand_id") Long id, @RequestParam(name = "country_id") Long countryId) {
        Brand brand = brandsServices.findById(id);
        vegetable.setBrand(brand);
        Country country = countryServices.findById(countryId);
        vegetable.setCountry(country);
        vegetableService.saveVegetable(vegetable);
        return "redirect:/admin/products";
    }
}
