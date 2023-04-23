package com.example.webshop.services;

import com.example.webshop.models.models.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    @Override
    public void sendCartChangedService(ShoppingCart cart) {
        System.out.println("Shopping cart was changed");
    }
}
