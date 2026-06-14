package com.training.structural.flyweight.fix;

public class CartItem {
    // Extrinsic data unique to THIS specific user's cart
    private final int quantity;
    private final double userSpecificPrice;

    // Shared reference pointer to the heavy Flyweight data (takes almost 0 memory)
    private final ProductSpecification specification;

    public CartItem(int quantity, double price, ProductSpecification spec) {
        this.quantity = quantity;
        this.userSpecificPrice = price;
        this.specification = spec;
    }

    public void renderCartRow() {
        // Pass the extrinsic state to the intrinsic object method
        specification.displayItemDetails(quantity, userSpecificPrice);
    }
}