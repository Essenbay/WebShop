package com.example.webshop.services;

import com.example.webshop.models.models.Brand;
import com.example.webshop.repositories.BrandsRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("singleton")
public class BrandsServices {
    private final BrandsRepository brandsRepository;

    public BrandsServices(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    public List<Brand> getAllBrands() {
        return brandsRepository.findAll();
    }

    public Brand findById(Long id){
        return brandsRepository.getReferenceById(id);
    }

}
