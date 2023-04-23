package com.example.webshop.services;

import com.example.webshop.models.models.*;
import com.example.webshop.repositories.GardenProductsRepository;
import com.example.webshop.util.NotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class GardenProductsServiceImpl implements GardenProductService {
    private final GardenProductsRepository gardenProductsRepository;

    public GardenProductsServiceImpl(GardenProductsRepository gardenProductsRepository) {
        this.gardenProductsRepository = gardenProductsRepository;
    }

    @Override
    public List<GardenProduct> getAllProducts() {
        return gardenProductsRepository.findAll();
    }


    public void deleteProductById(Long id) {
        try {
            gardenProductsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Item not found");
        }
    }

    @Override
    public void saveProduct(GardenProduct product) {
        gardenProductsRepository.save(product);
    }

    @Override
    public GardenProduct getProductById(Long id) {
        Optional<GardenProduct> product = gardenProductsRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    public GardenProductFactory getFactory(GardenProductTypes type){
        if (type == GardenProductTypes.FRUIT) {
            return new FruitFactory();
        } else if (type == GardenProductTypes.VEGETABLE) {
            return new VegetableFactory();
        } else {
            throw new IllegalArgumentException("Invalid product type: " + type);
        }
    }
}
