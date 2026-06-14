package com.training.structural.flyweight.fix;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalogFactory {
    private static final Map<String, ProductSpecification> catalog = new HashMap<>();

    public static ProductSpecification getProductSpec(String id, String title, String desc, byte[] img) {
        // If the product details are already in memory, reuse them!
        if (!catalog.containsKey(id)) {
            System.out.println("-> Loading heavy product data into catalog cache for ID: " + id);
            catalog.put(id, new ProductSpecification(id, title, desc, img));
        }
        return catalog.get(id);
    }
}