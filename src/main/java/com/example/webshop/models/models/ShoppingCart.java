package com.example.webshop.models.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<GardenProduct> items;

    public ShoppingCart() {
        this.items = new ArrayList<>(0);
    }

    public ShoppingCart(List<GardenProduct> items) {
        this.items = items;
    }

    public void addItem(GardenProduct item) {
        items.add(item);
    }

    public void removeItem(GardenProduct item) {
        items.remove(item);
    }

    public List<GardenProduct> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public int getItemCount() {
        return items.size();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (GardenProduct item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

}
