package com.example.webshop.controllers;

import com.example.webshop.models.models.GardenProduct;
import com.example.webshop.models.models.ShoppingCart;
import com.example.webshop.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("shoppingCart")
public class CustomerController {
    private final GardenProductsServiceImpl gardenProductsService;
    private final FruitsService fruitsService;
    private final VegetableService vegetableService;
    private final BrandsServices brandsServices;
    private final CartService cartService;

    @Autowired
    public CustomerController(GardenProductsServiceImpl gardenProductsService, FruitsService fruitsService, VegetableService vegetableService, BrandsServices brandsServices, CartService cartService) {
        this.gardenProductsService = gardenProductsService;
        this.fruitsService = fruitsService;
        this.vegetableService = vegetableService;
        this.brandsServices = brandsServices;
        this.cartService = cartService;

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

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) return "redirect:/login";

        ShoppingCart cart = cartService.getShoppingCart();

        model.addAttribute("products", cart.getItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "customer/cart";
    }

    @GetMapping("cart/add-item/{id}")
    public String addToCart(Model model, @PathVariable("id") Long id) {
        ShoppingCart cart = cartService.getShoppingCart();
        cartService.addItemToCart(id);
        return "redirect:/catalog";
    }
}

