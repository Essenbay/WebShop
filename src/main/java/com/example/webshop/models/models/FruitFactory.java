package com.example.webshop.models.models;

import com.example.webshop.services.GardenProductsService;

public class FruitFactory {
    private GardenProductsService service;

    public FruitFactory(GardenProductsService service) {
        this.service = service;
    }

    Fruit create(Brand brand) {
        Fruit result = new Fruit();
        result.setBrand(brand);
        service.saveFruit(result);
        return result;
    }
}
