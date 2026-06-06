package com.training.lsp.bad;

/**
 * A classic real-world violation is the Square-Rectangle problem. A square is mathematically a rectangle,
 * but forcing it into that inheritance relationship breaks software expectations.
 */
class Rectangle {
    protected int width, height;
    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
    public int getArea() { return width * height; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w) { super.setWidth(w); super.setHeight(w); } // Forces both to match
    @Override
    public void setHeight(int h) { super.setWidth(h); super.setHeight(h); }
}