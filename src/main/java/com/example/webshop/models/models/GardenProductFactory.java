package com.example.webshop.models.models;

import com.example.webshop.services.FruitsService;
import com.example.webshop.services.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public interface GardenProductFactory {
    GardenProduct create();
}

