package com.example.webshop.models.factories;

import com.example.webshop.models.models.Brand;
import com.example.webshop.models.models.Fruit;
import com.example.webshop.models.models.GardenProduct;
import com.example.webshop.models.models.Vegetable;
import com.example.webshop.services.GardenProductsService;

public class ProductFactory {
    public static void createProduct(GardenProduct product, Brand brand, GardenProductsService service) {
        product.setBrand(brand);
        if (product instanceof Fruit) {
            service.saveFruit((Fruit) product);
        } else if (product instanceof Vegetable) {
            service.saveVegetable((Vegetable) product);
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }
    }
}
