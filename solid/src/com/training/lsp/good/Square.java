package com.training.lsp.good;

/**
 * If a subclass cannot fulfill the behavior promised by the parent class,
 * they shouldn't share that inheritance chain.
 * Instead, abstract their commonality up or separate them.
 */
interface Shape {
    int getArea();
}

class Rectangle implements Shape {
    private int width, height;
    public Rectangle(int w, int h) { this.width = w; this.height = h; }
    public int getArea() { return width * height; }
}

class Square implements Shape {
    private int side;
    public Square(int side) { this.side = side; }
    public int getArea() { return side * side; }
}