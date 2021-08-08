package org.litescipt.loader.lang;

import org.litescipt.LiteScipt;
import org.litescipt.loader.Class;

import java.util.ArrayList;

public class ObjectMath extends Object {

    public String code;
    public Class target;
    public int line;

    public ObjectMath(Class target, String code, int line) {
        this.code = code;
        this.target = target;
        this.line = line;
    }

    public int getIntValue() {
        int sum = 0;
        ArrayList<String> math1Arr = new ArrayList<>();
        code = target.deleteString2(code, ')');
        if (code.contains("(")) {
            String[] math1 = code.split("\\(");//最高级
            int length = math1.length;//最高级
            for (int i = length; i > 0; i--) {
                math1Arr.add(math1[i - 1]);
            }
        } else {
            String[] math1 = new String[1];
            math1[0] = code;
            int length = math1.length;
            for (int i = length; i > 0; i--) {
                math1Arr.add(math1[i - 1]);
            }
        }
        for (String str : math1Arr) {
            if (str.contains("+")) {
                String[] math2 = str.split("\\+");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        int value2 = objectMath.getIntValue();
                        sum += value2;
                    }
                }
            } else if (str.contains("-")) {
                String[] math2 = str.split("\\-");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        int value2 = objectMath.getIntValue();
                        sum -= value2;
                    }
                }
            } else if (str.contains("*")) {
                String[] math2 = str.split("\\*");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        int value2 = objectMath.getIntValue();
                        sum *= value2;
                    }
                }
            } else if (str.contains("/")) {
                String[] math2 = str.split("\\/");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        int value2 = objectMath.getIntValue();
                        sum /= value2;
                    }
                }
            } else {
                sum += Integer.valueOf(str);
            }
        }
        return sum;
    }

    public double getDoubleValue() {
        double sum = 0D;
        code = target.deleteString2(code, ')');
        String[] math1 = code.split("\\(");//最高级
        int length = math1.length;//最高级
        ArrayList<String> math1Arr = new ArrayList<>();
        for (int i = length; i > 0; i--) {
            math1Arr.add(math1[i - 1]);
        }
        for (String str : math1Arr) {
            if (str.contains("+")) {
                String[] math2 = str.split("\\+");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        double value2 = objectMath.getDoubleValue();
                        sum += value2;
                    }
                }
            } else if (str.contains("-")) {
                String[] math2 = str.split("\\-");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        double value2 = objectMath.getDoubleValue();
                        sum -= value2;
                    }
                }
            } else if (str.contains("*")) {
                String[] math2 = str.split("\\*");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        double value2 = objectMath.getDoubleValue();
                        sum *= value2;
                    }
                }
            } else if (str.contains("/")) {
                String[] math2 = str.split("\\/");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.verIntHashMap.containsKey(valueStr) || target.verDoubleHashMap.containsKey(valueStr)) {
                            if (target.verIntHashMap.containsKey(valueStr)) sum += target.verIntHashMap.get(valueStr);
                            if (target.verDoubleHashMap.containsKey(valueStr))
                                sum += target.verDoubleHashMap.get(valueStr);
                        } else {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                    + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return 0;
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line);
                        double value2 = objectMath.getDoubleValue();
                        sum /= value2;
                    }
                }
            } else {
                sum += Double.valueOf(str);
            }
        }
        return sum;
    }

    public boolean check(String str1) {
        if (str1.contains("+")) return true;
        if (str1.contains("-")) return true;
        if (str1.contains("*")) return true;
        if (str1.contains("/")) return true;
        return false;
    }

}
