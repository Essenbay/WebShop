package com.example.webshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "fruits")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Fruit extends GardenProduct {

    @Override
    public Boolean toUtilization() {
        return true;
    }
}
