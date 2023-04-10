package com.example.webshop.services;


import com.example.webshop.models.models.Fruit;
import com.example.webshop.models.models.Vegetable;

public interface GardenProductsService {
    void saveFruit(Fruit fruit);

    void saveVegetable(Vegetable vegetable);

    void deleteProductById(Long id);
}