package com.example.webshop.controllers;

import com.example.webshop.models.Item;
import com.example.webshop.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String findAll(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        System.out.println(items.toString());
        return "item-list";
    }

    @GetMapping("/item-create")
    public String createItemForm(Item item, Model model) {
        model.addAttribute("item", item);
        return "item-create";
    }

    @PostMapping("/item-create")
    public String createItem(Item item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("item-delete/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }

    @GetMapping("/item-update/{id}")
    public String updateItemForm(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "item-update";
    }

    @PostMapping("/item-update")
    public String updateItem(Item item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
