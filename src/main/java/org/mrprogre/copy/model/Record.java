package org.mrprogre.copy.model;

public class Record {
    private final String name;
    private final int quantity;

    public Record(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}