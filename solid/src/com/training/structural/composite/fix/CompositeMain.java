package com.training.structural.composite.fix;

import java.util.ArrayList;
import java.util.List;

// 1. Unified Interface
interface FileSystemNode {
    void print(String structure);
}

// 2. Leaf Node (Individual Object)
class ConcreteFile implements FileSystemNode {
    private String name;
    public ConcreteFile(String name) { this.name = name; }

    @Override
    public void print(String structure) {
        System.out.println(structure + "📄 File: " + name);
    }
}

// 3. Composite Node (Group of Objects)
class ConcreteFolder implements FileSystemNode {
    private String name;
    private List<FileSystemNode> children = new ArrayList<>(); // Tracks everything under one interface!

    public ConcreteFolder(String name) { this.name = name; }
    public void add(FileSystemNode node) { children.add(node); }

    @Override
    public void print(String structure) {
        System.out.println(structure + "📁 Folder: " + name);
        for (FileSystemNode child : children) {
            child.print(structure + "  "); // Polymorphic recursion handled automatically!
        }
    }
}

// Sample Main Execution
public class CompositeMain {
    public static void main(String[] args) {
        ConcreteFolder root = new ConcreteFolder("Root");
        ConcreteFolder subDir = new ConcreteFolder("Documents");

        root.add(new ConcreteFile("system.log"));
        subDir.add(new ConcreteFile("resume.pdf"));
        root.add(subDir); // Folders hold Folders seamlessly!

        root.print("");
    }
}