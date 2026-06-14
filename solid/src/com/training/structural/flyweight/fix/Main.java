package com.training.structural.flyweight.fix;

public class Main {
    public static void main(String[] args) {
        // Mock heavy image asset (2MB)
        byte[] iphoneImage = new byte[2 * 1024 * 1024];

        System.out.println("=== 🛒 User A Adds iPhone to Cart ===");
        ProductSpecification specA = ProductCatalogFactory.getProductSpec("IPHONE15", "iPhone 15", "Apple Smartphone", iphoneImage);
        CartItem userACartItem = new CartItem(1, 999.00, specA);
        userACartItem.renderCartRow();

        System.out.println("\n=== 🛒 User B Adds SAME iPhone to Cart ===");
        // Notice it won't load the heavy data again! It fetches from the factory cache.
        ProductSpecification specB = ProductCatalogFactory.getProductSpec("IPHONE15", "iPhone 15", "Apple Smartphone", iphoneImage);
        CartItem userBCartItem = new CartItem(2, 949.00, specB); // User B has a corporate discount price!
        userBCartItem.renderCartRow();
    }
}
