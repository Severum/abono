package fr.eni.abono.models;

public enum Priority {
    INDISPENSABLE("Indispensable"),
    IMPORTANT("Important"),
    OPTIONAL("Optional");

    private String label;

    Priority(String label){
        this.label = label;
    }

    public String toString(){
        return label;
    }
}
