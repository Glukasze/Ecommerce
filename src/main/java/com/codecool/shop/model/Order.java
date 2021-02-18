package com.codecool.shop.model;

import java.util.ArrayList;

public class Order {

    private static ArrayList<Product> productsOrdered = new ArrayList<>();

    public Order() {
    }

    public void clearProductsOrdered() {
        Order.productsOrdered.clear();
    }

    public void addProduct(Product product) {
        productsOrdered.add(product);
    }

    public void removeProduct(Product product) {
        productsOrdered.remove(product);
    }

    public ArrayList<Product> getProductsOrdered() {
        return productsOrdered;
    }

    public String getTotalPrice() {
        float temp = 0f;
        for (Product product:productsOrdered) {
            temp += product.getDefaultPrice();
        }
        return String.valueOf(temp) + " USD";
    }

}
