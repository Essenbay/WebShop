package com.example.webshop.repositories;

import com.example.webshop.models.models.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitsRepository extends JpaRepository<Fruit, Long> {
}
