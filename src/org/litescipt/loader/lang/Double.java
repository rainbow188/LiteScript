package org.litescipt.loader.lang;

public class Double extends Object {

    private String name;
    private double value;

    public Double(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
