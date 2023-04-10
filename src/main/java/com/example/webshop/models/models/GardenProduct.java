package com.example.webshop.models.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "garden_products")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class GardenProduct implements Expirationable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, name = "expirationdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(nullable = false, name = "volume")
    private String volume;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @Override
    public Boolean toUtilization() {
        return true;
    }

    public boolean isFruit() {
        return this instanceof Fruit;
    }

    public boolean isVegetable() {
        return this instanceof Vegetable;
    }
}
