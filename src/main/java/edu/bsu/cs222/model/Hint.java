package edu.bsu.cs222.model;

public enum Hint {
    SEMICORRECT("*"), INCORRECT("-");
    
    Hint(String s) {
    }

    public String getValue() {
        if (this.ordinal() == 0){
            return "*";
        } else {
            return "-";
        }
    }
}
