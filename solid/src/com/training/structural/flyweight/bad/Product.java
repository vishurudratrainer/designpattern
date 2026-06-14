package com.training.structural.flyweight.bad;

public class Product {
    // Intrinsic (Shared) State - This takes up 95% of the memory!
    private String productId;
    private String title;
    private String description;
    private byte[] highResImage; // Heavy 2MB image asset

    // Extrinsic (Unique) State - This takes up 5% of the memory
    private int quantity;
    private double selectedPrice; // Price might vary based on user discounts

    public Product(String id, String title, String desc, byte[] img, int qty, double price) {
        this.productId = id;
        this.title = title;
        this.description = desc;
        this.highResImage = img;
        this.quantity = qty;
        this.selectedPrice = price;
    }
}