package com.example.webshop.repositories;

import com.example.webshop.models.models.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VegetablesRepository extends JpaRepository<Vegetable, Long> {
}
