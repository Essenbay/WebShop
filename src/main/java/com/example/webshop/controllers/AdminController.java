package com.example.webshop.controllers;

import com.example.webshop.models.dto.UserDto;
import com.example.webshop.models.models.*;
import com.example.webshop.services.*;
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


//Todo: Implement Factory pattern
//Todo: Implement singleton to shopping cart
//Todo: Implement strategy
@Controller
public class AdminController {
    private final GardenProductsServiceImpl gardenProductsService;
    private final FruitsService fruitsService;
    private final VegetableService vegetableService;
    private final BrandsServices brandsServices;

    private UserService userService;

    public AdminController(GardenProductsServiceImpl gardenProductsService, FruitsService fruitsService, VegetableService vegetableService, BrandsServices brandsServices, UserService userService) {
        this.gardenProductsService = gardenProductsService;
        this.fruitsService = fruitsService;
        this.vegetableService = vegetableService;
        this.brandsServices = brandsServices;
        this.userService = userService;
    }

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

    @PostMapping("/admin/products/fruit-create")
    public String createFruit(Fruit fruit, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        fruit.setBrand(brand);
        gardenProductsService.saveFruit(fruit);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/vegetable-create")
    public String createVegetableForm(Vegetable vegetable, Model model) {
        model.addAttribute("vegetable", vegetable);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        return "admin/vegetable-create";
    }

    @PostMapping("/admin/products/vegetable-create")
    public String createVegetable(Vegetable vegetable, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        vegetable.setBrand(brand);
        gardenProductsService.saveVegetable(vegetable);
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
        return "admin/fruit-update";
    }

    @PostMapping("/admin/products/fruit-update")
    public String updateFruit(Fruit fruit, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        fruit.setBrand(brand);
        fruitsService.saveFruit(fruit);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/vegetable-update/{id}")
    public String updateVegetableForm(@PathVariable("id") Long id, Model model) {
        Vegetable vegetable = vegetableService.getVegetableById(id);
        model.addAttribute("vegetable", vegetable);
        List<Brand> brands = brandsServices.getAllBrands();
        model.addAttribute("brands", brands);
        return "admin/vegetable-update";
    }

    @PostMapping("/admin/products/vegetable-update")
    public String updateVegetable(Vegetable vegetable, @RequestParam(name = "brand_id") Long id) {
        Brand brand = brandsServices.findById(id);
        vegetable.setBrand(brand);
        vegetableService.saveVegetable(vegetable);
        return "redirect:/admin/products";
    }
}
