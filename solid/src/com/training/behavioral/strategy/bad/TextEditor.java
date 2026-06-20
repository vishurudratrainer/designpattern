package com.training.behavioral.strategy.bad;

class TextEditor {
    public void saveFile(String content, String format) {
        if (format.equalsIgnoreCase("PDF")) {
            System.out.println("Applying complex PDF rendering algorithms...");
        } else if (format.equalsIgnoreCase("HTML")) {
            System.out.println("Converting text to HTML markup tags...");
        } else if (format.equalsIgnoreCase("JSON")) {
            System.out.println("Parsing characters into JSON key-value pairs...");
        }
    }
}


/**
 *
 * The Flaw: Mixing completely different file-parsing algorithms inside one single method. It makes the class incredibly long, difficult to read, and difficult to test independently.
 *
 */