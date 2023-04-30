package com.example.webshop.repositories;

import com.example.webshop.models.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}