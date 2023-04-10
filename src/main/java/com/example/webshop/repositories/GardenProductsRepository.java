package com.example.webshop.repositories;

import com.example.webshop.models.models.GardenProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenProductsRepository extends JpaRepository<GardenProduct, Long> {
}