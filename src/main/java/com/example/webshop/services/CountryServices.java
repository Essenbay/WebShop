package com.example.webshop.services;

import com.example.webshop.models.models.Country;
import com.example.webshop.repositories.CountryRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("singleton")
public class CountryServices {
    private final CountryRepository countryRepository;

    public CountryServices(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country findById(Long id){
        return countryRepository.getReferenceById(id);
    }

}