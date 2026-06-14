package com.training.structural.decorator.fix;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. Ordering a Plain Coffee ---");
        Coffee basicCoffee = new SimpleCoffee();
        printOrder(basicCoffee);

        System.out.println("\n--- 2. Dynamically Adding Milk ---");
        // We wrap the basic coffee inside a Milk decorator
        Coffee milkCoffee = new Milk(basicCoffee);
        printOrder(milkCoffee);

        System.out.println("\n--- 3. Stacking Multiple Decorators (Milk + Caramel) ---");
        // We wrap the milk coffee inside a Caramel decorator
        Coffee customFancyCoffee = new Caramel(milkCoffee);
        printOrder(customFancyCoffee);

        System.out.println("\n--- 4. All-In-One Line (Double Milk + Caramel) ---");
        // This is how decorators are usually constructed in production systems
        Coffee ultraCoffee = new Caramel(new Milk(new Milk(new SimpleCoffee())));
        printOrder(ultraCoffee);
    }

    // Helper method to display output to the class
    private static void printOrder(Coffee coffee) {
        System.out.println("Receipt: " + coffee.getDescription());
        System.out.println("Total Cost: $" + String.format("%.2f", coffee.getCost()));
    }
}
