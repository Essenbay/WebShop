package com.example.webshop.services;

import com.example.webshop.models.Fruit;
import com.example.webshop.models.GardenProduct;
import com.example.webshop.models.Vegetable;
import com.example.webshop.repositories.GardenProductsRepository;
import com.example.webshop.util.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GardenProductsService {
    private final GardenProductsRepository gardenProductsRepository;

    public GardenProductsService(GardenProductsRepository gardenProductsRepository) {
        this.gardenProductsRepository = gardenProductsRepository;
    }

    public void saveProduct(GardenProduct product) {
        gardenProductsRepository.save(product);
    }

    public void saveFruit(Fruit fruit) {
        gardenProductsRepository.save(fruit);
    }

    public void saveVegetable(Vegetable vegetable) {
        gardenProductsRepository.save(vegetable);
    }

    public void deleteProductById(Long id) {
        try {
            gardenProductsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Item not found");
        }
    }
}
