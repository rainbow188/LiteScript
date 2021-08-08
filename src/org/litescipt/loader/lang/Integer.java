package org.litescipt.loader.lang;

public class Integer extends Object {

    private String name;
    private int value;

    public Integer(String name,int value) {
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
