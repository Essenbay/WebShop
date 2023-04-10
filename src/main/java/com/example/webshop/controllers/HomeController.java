package com.example.webshop.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String findAll(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isNotAuthorized = authentication == null || authentication.getPrincipal().equals("anonymousUser");
        boolean hasAdminRole;
        if (isNotAuthorized) {
            hasAdminRole = false;
        } else {
            hasAdminRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        }
        return null;
    }
}
