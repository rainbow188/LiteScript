package org.litescipt.loader;

import java.util.ArrayList;

public class Symbol {

    public static final String TYPE_MATH = "TYPE_MATH";
    public static final String TYPE_KEY = "TYPE_KEY";
    public static final String TYPE_CHAR = "TYPE_CHAR";

    public static ArrayList<String> symbol_math = new ArrayList<>();
    public static ArrayList<String> symbol_key = new ArrayList<>();
    public static ArrayList<String> symbol_char = new ArrayList<>();

    public static String getType(String str) {
        symbol_math.add("+");
        symbol_math.add("-");
        symbol_math.add("*");
        symbol_math.add("/");
        symbol_key.add("int");
        symbol_key.add("fun");
        symbol_key.add("return");
        symbol_key.add("print");
        symbol_key.add("import");
        symbol_char.add("}");
        for (String str1 : symbol_key) {
            if (str.contains(str1)) return TYPE_KEY;
        }
        for (String str2 : symbol_math) {
            if (str.contains(str2)) return TYPE_MATH;
        }
        for (String str3 : symbol_char) {
            if (str.contains(str3)) return TYPE_CHAR;
        }
        return null;
    }

}
