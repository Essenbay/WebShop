package com.example.webshop.repositories;

import com.example.webshop.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brand, Long> {
}

