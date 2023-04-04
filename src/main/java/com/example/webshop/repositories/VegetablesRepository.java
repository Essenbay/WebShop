package com.example.webshop.repositories;

import com.example.webshop.models.Fruit;
import com.example.webshop.models.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VegetablesRepository extends JpaRepository<Vegetable, Long> {
}
