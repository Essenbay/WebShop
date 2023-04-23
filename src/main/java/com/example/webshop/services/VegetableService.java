package com.example.webshop.services;

import com.example.webshop.models.models.Vegetable;
import com.example.webshop.repositories.VegetablesRepository;
import com.example.webshop.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class VegetableService {

    private final VegetablesRepository vegetablesRepository;

    @Autowired
    public VegetableService(VegetablesRepository vegetablesRepository) {
        this.vegetablesRepository = vegetablesRepository;
    }

    public List<Vegetable> getAllVegetables() {
        return vegetablesRepository.findAll();
    }

    public Vegetable getVegetableById(Long id) {
        Optional<Vegetable> gardenProductOptional = vegetablesRepository.findById(id);
        if (gardenProductOptional.isPresent()) {
            return gardenProductOptional.get();
        } else {
            throw new NotFoundException("Garden product not found");
        }
    }

    public void saveVegetable(Vegetable vegetable) {
        vegetablesRepository.save(vegetable);
    }
}
