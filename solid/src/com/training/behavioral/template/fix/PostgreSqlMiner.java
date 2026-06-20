package com.training.behavioral.template.fix;

abstract class DataMinerTemplate {
    // 'final' stops subclasses from rearranging the execution order
    public final void mine() {
        openFile();
        parseData();
        closeFile();
    }

    private void openFile() { System.out.println("Opening file stream..."); }
    private void closeFile() { System.out.println("Closing file stream..."); }

    // Subclasses must build their own specific details for this step
    protected abstract void parseData();
}

class PostgreSqlMiner extends DataMinerTemplate {
    @Override
    protected void parseData() { System.out.println("Parsing SQL rows..."); }
}

class Test{
    public static void main(String[] args) {
        DataMinerTemplate template = new PostgreSqlMiner();
        template.mine();
    }
}