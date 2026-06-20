package com.training.behavioral.memento.fix;

import java.util.Stack;

// 1. The Memento Blueprint (Immutable Object Snapshot)
class EditorMemento {
    private final String contentState;
    public EditorMemento(String state) { this.contentState = state; }
    protected String getState() { return contentState; } // Package-private/restricted
}

// 2. The Originator (The Class to be Backed Up)
class TextEditor {
    private String content;

    public void typeText(String text) { this.content = text; }
    public String getContent() { return content; }

    // Creates the Snapshot
    public EditorMemento save() { return new EditorMemento(content); }

    // Restores the Snapshot
    public void restore(EditorMemento memento) {
        if (memento != null) {
            this.content = memento.getState();
        }
    }
}

// 3. The Caretaker (Manages History Timelines)
class HistoryManager {
    private final Stack<EditorMemento> history = new Stack<>();

    public void pushSnapshot(EditorMemento m) { history.push(m); }
    public EditorMemento popSnapshot() { return history.isEmpty() ? null : history.pop(); }
}

// Sample Main Execution
public class MementoMain {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        HistoryManager caretaker = new HistoryManager();

        editor.typeText("Version 1");
        caretaker.pushSnapshot(editor.save()); // Save checkpoint

        editor.typeText("Version 2 (With typos!)");
        System.out.println("Current: " + editor.getContent());

        editor.restore(caretaker.popSnapshot()); // Rollback
        System.out.println("Restored: " + editor.getContent());
    }
}
