package com.example.webshop.models.models;

public class VegetableFactory implements GardenProductFactory {
    @Override
    public GardenProduct create() {
        return new Vegetable();
    }
}
