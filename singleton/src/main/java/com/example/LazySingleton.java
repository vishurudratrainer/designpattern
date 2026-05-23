package com.example;
public class LazySingleton {

    // 1. Private constructor: Stops anyone from typing 'new LazySingleton()' outside this class.
    private LazySingleton() {
        System.out.println("Constructor called! LazySingleton object created.");
    }

    // 2. The Inner Class Magic: The JVM completely ignores this class when 'LazySingleton' starts up.
    // It will NOT load into memory, and the instance won't be created until line 17 is executed.
    private static class Holder {
        // The JVM guarantees that this initialization is thread-safe and happens exactly once.
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    // 3. Global Access Point: Calling this is what triggers the inner class to load.
    public static LazySingleton getInstance() {
        System.out.println("getInstance() called...");
        // Accessing 'Holder.INSTANCE' forces the JVM to load the Holder class right now (Lazy Loading).
        return Holder.INSTANCE;
    }

    public void doSomething() {
        System.out.println("LazySingleton is working fine!");
    }

    // --- QUICK TEST ---
    // You can run this main method to watch the lazy loading happen in real-time.
    public static void main(String[] args) {
        System.out.println("Application started. Notice the constructor hasn't run yet!");
        System.out.println("---------------------------------------------------------");

        // This first call triggers the inner class to load and run the constructor
        LazySingleton s1 = LazySingleton.getInstance();
        s1.doSomething();
        System.out.println("---------------------------------------------------------");

        // This second call bypasses the constructor entirely and just grabs the existing instance
        LazySingleton s2 = LazySingleton.getInstance();

        // Confirms both variables point to the exact same memory address
        System.out.println("Are both instances identical? " + (s1 == s2));
    }
}