package com.example.webshop.services;

import com.example.webshop.models.models.ShoppingCart;

public interface INotificationService {
    void sendCartChangedService(ShoppingCart cart);
}
