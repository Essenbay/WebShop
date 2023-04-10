package com.example.webshop.services;

import com.example.webshop.models.models.Fruit;
import com.example.webshop.models.models.Vegetable;
import com.example.webshop.repositories.GardenProductsRepository;
import com.example.webshop.util.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GardenProductsServiceImpl implements GardenProductsService {
    private final GardenProductsRepository gardenProductsRepository;

    public GardenProductsServiceImpl(GardenProductsRepository gardenProductsRepository) {
        this.gardenProductsRepository = gardenProductsRepository;
    }

    @Override
    public void saveFruit(Fruit fruit) {
        gardenProductsRepository.save(fruit);
    }

    @Override
    public void saveVegetable(Vegetable vegetable) {
        gardenProductsRepository.save(vegetable);
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            gardenProductsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Item not found");
        }
    }
}
