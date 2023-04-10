package com.example.webshop.models.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "vegetables")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Vegetable extends GardenProduct {

    @Override
    public Boolean toUtilization() {
        return true;
    }
}
