package com.training.structural.decorator.fix;
// 1. The Component Interface
interface Coffee {
    String getDescription();
    double getCost();
}

// 2. The Concrete Component (The Base Object)
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 2.00; // Base price $2.00
    }
}

// 3. The Base Decorator Class (Implements Component AND Wraps Component)
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee; // The object being wrapped

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// 4. Concrete Decorator A: Milk
class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50; // Adding Milk costs $0.50
    }
}

// 5. Concrete Decorator B: Caramel
class Caramel extends CoffeeDecorator {
    public Caramel(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Caramel";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.75; // Adding Caramel costs $0.75
    }
}