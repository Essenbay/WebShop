package com.example.webshop.services;

import com.example.webshop.models.models.GardenProduct;
import com.example.webshop.models.models.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    public CartService(HttpSession session, GardenProductService gardenProductService) {
        this.session = session;
        this.gardenProductService = gardenProductService;
    }

    private HttpSession session;
    private GardenProductService gardenProductService;

    @Autowired
    private INotificationService notificationService;
    private static final String SESSION_CART_KEY = "shoppingCart";

    public void addItemToCart(Long productId) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SESSION_CART_KEY);
        if (cart == null) {
            cart = new ShoppingCart();
        }
        GardenProduct product = gardenProductService.getProductById(productId);
        cart.addItem(product);
        session.setAttribute(SESSION_CART_KEY, cart);

        notificationService.sendCartChangedService(cart);
    }

    public void removeItemFromCart(Long productId) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SESSION_CART_KEY);
        if (cart != null) {
            GardenProduct product = gardenProductService.getProductById(productId);
            cart.removeItem(product);
            session.setAttribute(SESSION_CART_KEY, cart);
        }
    }

    public ShoppingCart getShoppingCart() {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SESSION_CART_KEY);
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute(SESSION_CART_KEY, cart);
        }
        return cart;
    }

    public void clearShoppingCart() {
        session.removeAttribute(SESSION_CART_KEY);
    }
}
