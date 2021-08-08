package org.litescipt.loader.lang;

import org.litescipt.LiteScipt;
import org.litescipt.loader.Class;

import java.util.ArrayList;

public class ObjectMath extends Object {

    public String code;
    public Class target;
    public int line;
    public ObjectMethod objectMethod;

    public ObjectMath(Class target, String code, int line, ObjectMethod objectMethod) {
        this.code = code;
        this.target = target;
        this.line = line;
        this.objectMethod = objectMethod;
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
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum += ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum += ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum += ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum += ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        int value2 = objectMath.getIntValue();
                        sum += value2;
                    }
                }
            } else if (str.contains("-")) {
                String[] math2 = str.split("\\-");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum -= ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum -= ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum -= ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum -= ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        int value2 = objectMath.getIntValue();
                        sum -= value2;
                    }
                }
            } else if (str.contains("*")) {
                String[] math2 = str.split("\\*");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum *= ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum *= ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum *= ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum *= ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        int value2 = objectMath.getIntValue();
                        sum *= value2;
                    }
                }
            } else if (str.contains("/")) {
                String[] math2 = str.split("\\/");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum /= ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum /= ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum /= ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum /= ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        int value2 = objectMath.getIntValue();
                        sum /= value2;
                    }
                }
            } else {
                sum += java.lang.Integer.valueOf(str);
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
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum += ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum += ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum += ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum += ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        double value2 = objectMath.getDoubleValue();
                        sum += value2;
                    }
                }
            } else if (str.contains("-")) {
                String[] math2 = str.split("\\-");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum -= ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum -= ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum -= ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum -= ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        double value2 = objectMath.getDoubleValue();
                        sum -= value2;
                    }
                }
            } else if (str.contains("*")) {
                String[] math2 = str.split("\\*");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum *= ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum *= ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum *= ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum *= ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        double value2 = objectMath.getDoubleValue();
                        sum *= value2;
                    }
                }
            } else if (str.contains("/")) {
                String[] math2 = str.split("\\/");
                for (String valueStr : math2) {
                    if (!check(valueStr)) {
                        if (target.variableMap.containsKey(valueStr)) {
                            if (target.variableMap.get(valueStr) instanceof Integer)
                                sum /= ((Integer) target.variableMap.get(valueStr)).getValue();
                            else if (target.variableMap.get(valueStr) instanceof Double)
                                sum /= ((Double) target.variableMap.get(valueStr)).getValue();
                        } else {
                            if (objectMethod.argsValue.containsKey(valueStr)) {
                                if (objectMethod.argsValue.get(valueStr) instanceof Integer) {
                                    sum /= ((Integer) objectMethod.argsValue.get(valueStr)).getValue();
                                } else if (objectMethod.argsValue.get(valueStr) instanceof Double) {
                                    sum /= ((Double) objectMethod.argsValue.get(valueStr)).getValue();
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + target.name
                                        + "\nat " + target.name + "." + target.funWorker + "()[" + target.name + ".lsp:" + (line) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return 0;
                            }
                        }
                    } else {
                        ObjectMath objectMath = new ObjectMath(target, valueStr, line, objectMethod);
                        double value2 = objectMath.getDoubleValue();
                        sum /= value2;
                    }
                }
            } else {
                sum += java.lang.Double.valueOf(str);
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
