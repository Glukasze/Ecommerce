package com.codecool.shop.model;

import java.util.ArrayList;

public class Order {

    private ArrayList<Product> productsOrdered = new ArrayList<>();



    public void setProductsOrdered(ArrayList<Product> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public void addProduct(Product product) {
        this.productsOrdered.add(product);
    }

    public ArrayList<Product> getProductsOrdered() {
        return productsOrdered;
    }



}
