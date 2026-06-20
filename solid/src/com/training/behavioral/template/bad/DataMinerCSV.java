package com.training.behavioral.template.bad;

class DataMinerPDF {
    public void mineData() {
        System.out.println("Opening file stream..."); // Duplicate
        System.out.println("Parsing PDF specific text...");
        System.out.println("Closing file stream..."); // Duplicate
    }
}

class DataMinerCSV {
    public void mineData() {
        System.out.println("Opening file stream..."); // Duplicate
        System.out.println("Parsing CSV rows and commas...");
        System.out.println("Closing file stream..."); // Duplicate
    }
}


/**
 *
 * The Flaw: The overall steps and structural sequence of the algorithm are exactly identical across classes, creating bad code redundancy. If the way we "Open file stream" changes, you have to copy-paste the fix across every parser class.
 *
 */