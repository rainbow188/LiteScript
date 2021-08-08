package org.litescipt.loader.lang;

public class Boolean extends Object {

    private int value;

    public Boolean(boolean bool) {
        if (bool) {
            value = 1;
        } else {
            value = 0;
        }
    }

    public boolean toBoolean() {
        return value == 1;
    }

}
