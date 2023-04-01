package com.example.webshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "garden_product")
@Entity
public class GardenProduct extends Item {
    @Column
    private BigDecimal weight;

    @Override
    public Boolean toUtilization() {
        return true;
    }
}
