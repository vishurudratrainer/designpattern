package com.training.behavioral.memento.bad;

import java.util.ArrayList;
import java.util.List;

class BadTextEditor {
    private String content;

    public void setContent(String content) { this.content = content; }
    public String getContent() { return content; } // Exposing inner fields directly
}

class HistoryTracker {
    private List<String> states = new ArrayList<>();
    // System must manage and track the raw text content strings directly from outside,
    // exposing internal details to modifications.
    //historyManager.save(textEditor.getContent());
}

/**
 * The Flaw: Exposing internal class states directly via generic
 * public getters and setters leaves them vulnerable to external modification,
 * breaking the object's encapsulation and structural validation checks.
 */