package com.example.webshop.services;

import com.example.webshop.models.Fruit;
import com.example.webshop.models.GardenProduct;
import com.example.webshop.repositories.FruitsRepository;
import com.example.webshop.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitsService {
    private final FruitsRepository fruitsRepository;

    @Autowired
    public FruitsService(FruitsRepository fruitsRepository) {
        this.fruitsRepository = fruitsRepository;
    }

    public List<Fruit> getAllFruits() {
        return fruitsRepository.findAll();
    }

    public Fruit getFruitById(Long id) {
        Optional<Fruit> fruitOptional = fruitsRepository.findById(id);
        if (fruitOptional.isPresent()) {
            Fruit fruit = fruitOptional.get();
            return fruit;
        } else {
            throw new NotFoundException("Fruit not found");
        }
    }

    public void saveFruit(Fruit fruit) {
        fruitsRepository.save(fruit);
    }
}
