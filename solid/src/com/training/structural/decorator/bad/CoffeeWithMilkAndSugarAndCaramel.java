package com.training.structural.decorator.bad;

class SimpleCoffee {}
class CoffeeWithMilk extends SimpleCoffee {}
class CoffeeWithSugar extends SimpleCoffee {}
class CoffeeWithMilkAndSugar extends CoffeeWithMilk {}
class CoffeeWithMilkAndSugarAndCaramel extends CoffeeWithMilkAndSugar {}
// Soon you will have 50 classes just for different ingredient combinations!