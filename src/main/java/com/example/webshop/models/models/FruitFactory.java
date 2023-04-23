package com.example.webshop.models.models;

public class FruitFactory implements GardenProductFactory {
    @Override
    public GardenProduct create() {
        return new Fruit();
    }
}
