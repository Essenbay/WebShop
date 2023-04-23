package com.example.webshop.services;

import com.example.webshop.models.models.GardenProduct;
import com.example.webshop.models.models.GardenProductFactory;
import com.example.webshop.models.models.GardenProductTypes;

import java.util.List;

public interface GardenProductService {
    List<GardenProduct> getAllProducts();

    void deleteProductById(Long id);

    public void saveProduct(GardenProduct product);

    public GardenProduct getProductById(Long id);
    GardenProductFactory getFactory(GardenProductTypes type);
}
