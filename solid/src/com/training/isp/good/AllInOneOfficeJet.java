package com.training.isp.good;

/**
 * The SOLID Way: Segregated Interfaces
 * Break down the interfaces into cohesive, bite-sized components.
 */
interface Printer { void print(); }
interface Scanner { void scan(); }
interface FaxMachine { void fax(); }

// Simple printer only implements what it actually does
class EconomicPrinter implements Printer {
    public void print() { System.out.println("Printing document..."); }
}

// High-end office machine implements everything
class AllInOneOfficeJet implements Printer, Scanner, FaxMachine {
    public void print() { /* ... */ }
    public void scan() { /* ... */ }
    public void fax() { /* ... */ }
}