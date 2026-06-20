package com.training.behavioral.strategy.fix;

// 1. Define the Strategy Interface
interface SaveStrategy { void save(String content); }

// 2. Build Independent Strategies
class PdfSaveStrategy implements SaveStrategy {
    public void save(String content) { System.out.println("Complex PDF Logic."); }
}
class HtmlSaveStrategy implements SaveStrategy {
    public void save(String content) { System.out.println("HTML Markup Logic."); }
}
class CsvSaveStrategy implements  SaveStrategy {

    @Override
    public void save(String content) {
        System.out.println("Csv save");
    }
}
// 3. Context switches strategies seamlessly
class TextEditor {
    private SaveStrategy strategy;

    public void setStrategy(SaveStrategy strategy) { this.strategy = strategy; }
    public void executeSave(String content) { strategy.save(content); }
}

class Test{
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        SaveStrategy saveStrategy = new PdfSaveStrategy();
        editor.setStrategy(saveStrategy);
        editor.executeSave("This is my content");
        SaveStrategy csv = new CsvSaveStrategy();
        editor.setStrategy(csv);
        editor.executeSave("Save in csv");
    }
}