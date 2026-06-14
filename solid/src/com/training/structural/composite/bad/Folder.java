package com.training.structural.composite.bad;

import java.util.ArrayList;
import java.util.List;

class File {
    private String name;
    public File(String name) { this.name = name; }
    public void printName() { System.out.println("File: " + name); }
}

class Folder {
    private String name;
    private List<File> files = new ArrayList<>();
    private List<Folder> subFolders = new ArrayList<>(); // Rigid separate tracking

    public Folder(String name) { this.name = name; }
    public void addFile(File f) { files.add(f); }
    public void addFolder(Folder f) { subFolders.add(f); }

    // Hard to scale: must manually recurse through different lists
    public void printStructure() {
        System.out.println("Folder: " + name);
        for (File f : files) f.printName();
        for (Folder sub : subFolders) sub.printStructure(); // Recursive nightmare if types expand
    }
}

/**
 * The Flaw: If you add a new element type (like a Shortcut or SymbolicLink),
 * you must alter the Folder class, 4
 * create a new list for it, and modify the traversal algorithm,
 * violating the Open/Closed Principle.
 */