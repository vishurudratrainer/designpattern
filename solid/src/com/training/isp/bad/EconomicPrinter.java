package com.training.isp.bad;

interface SmartDevice {
    void print();
    void scan();
    void fax();
}

/**
 * An all-in-one printer interface forces a basic desktop printer to implement faxing, which it cannot do.
 */
class EconomicPrinter implements SmartDevice {
    public void print() { /* Printing logic */ }
    public void scan() { throw new UnsupportedOperationException("Scan not supported"); }
    public void fax() { throw new UnsupportedOperationException("Fax not supported"); }
}