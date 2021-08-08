package org.litescipt.loader;

import org.litescipt.LiteScipt;
import org.litescipt.loader.lang.Integer;
import org.litescipt.loader.lang.Double;
import org.litescipt.loader.lang.Object;
import org.litescipt.loader.lang.ObjectMath;
import org.litescipt.loader.lang.ObjectMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Class {

    public String name;
    public String path;
    public int workLine;
    public ArrayList<String> codeBuilder;
    public ArrayList<java.lang.Integer> functionCount;
    public ArrayList<String> functionList;

    /*
    Variable Method
     */
    public HashMap<String, Object> variableMap;

    public HashMap<String, ArrayList<String>> funCodeArrMap;
    public HashMap<String, Class> importClasses;

    public String funWorker;
    private HashMap<String, java.lang.Integer> funEndLine;
    public HashMap<String, String> funTypes;
    public HashMap<String, String> funArgs;
    public HashMap<String, ObjectMethod> funObjects;

    /*
        Judge Worker
     */
    public HashMap<String, ArrayList<String>> judgeArrMap;
    public String judgeWorker;

    public Class(String name, String path) {
        this.name = name;
        this.codeBuilder = new ArrayList<>();
        this.functionCount = new ArrayList<>();
        this.functionList = new ArrayList<>();
        this.path = path;
        this.funWorker = null;
        this.funEndLine = new HashMap<>();
        this.variableMap = new HashMap<>();
        this.funCodeArrMap = new HashMap<>();
        this.importClasses = new HashMap<>();
        this.judgeArrMap = new HashMap<>();
        this.judgeWorker = null;
        this.funTypes = new HashMap<>();
        this.funArgs = new HashMap<>();
        this.funObjects = new HashMap<>();
        this.workLine = 0;
    }

    public void run() {
        int i = 1;
        for (String string : codeBuilder) {
            this.workLine = i;
            string = string.trim();
            String[] code = string.split("\\s+");
            for (int j = 0; j < code.length; j++) {
                String cmd = code[j];
                String type = Symbol.getType(cmd);
                if (type != null) {
                    switch (type) {
                        case Symbol.TYPE_KEY:
                            if (code[j].equals("fun") && funWorker == null) {
                                String args = null;
                                String[] code2 = string.split("\\s+", 2);
                                String funName = code2[1];
                                if (!funName.contains("()")) {
                                    // System.out.print("\n" + funName + "\n");
                                    args = funName.substring(funName.indexOf("(") + 1, funName.lastIndexOf(")"));
                                    if (!(args.contains("int") || args.contains("double"))) {
                                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.NoMethodDefFoundError: " + name
                                                + "\nat " + name + ".main(args)[" + name + ".lsp:" + (i) + "]" +
                                                "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                        return;
                                    }
                                } else {
                                    if (j != 0) {
                                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.RuntimeError: " + name +
                                                "\nat " + "org.litescipt.loader.Class.run()[Class:??]" +
                                                "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]");
                                        return;
                                    }
                                }
                                funName = deleteString2(funName, '(');
                                funName = deleteString2(funName, ')');
                                if (args != null) funName = funName.replace(args, "");
                                if (funName.contains("{")) funName = funName.replace("{", "");
                                else if (!code[j + 2].equals("{")) {
                                    LiteScipt.instance.consoleSender.call("org.litescipt.lang.NoMethodCharacterDefFoundError: " + name
                                            + "\nat " + name + ".main(args)[" + name + ".lsp:" + (i) + "]" +
                                            "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                    return;
                                }
                                String funType = "void";
                                if (funName.contains(":")) {
                                    funType = funName.split("\\:", 2)[1];
                                    if (!(funType.equals("int") || funType.equals("double"))) {
                                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.NoMethodCharacterDefFoundError: " + name
                                                + "\nat " + name + ".main(args)[" + name + ".lsp:" + (i) + "]" +
                                                "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                        return;
                                    }
                                }
                                if (funType != "void") funName = funName.replace(":" + funType, "");
                                functionList.add(funName);
                                funWorker = funName;
                                functionCount.add(i);
                                if (args == null) args = "void";
                                funArgs.put(funName, args);
                                funTypes.put(funName, funType);
                                //System.out.print("\n--------------\n函数创建: " + funName + "\n开始: " + i + "\n参数: " + funArgs.get(funName) + "\n返回类型:" + funTypes.get(funName) + "\n-----------------\n");
                                //System.out.print("创建函数: " + funName + " 起点:" + functionCount.get(functionCount.size() - 1));
                                LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + funName + "[" + name + ":" + (i) + "]");
                            } else if ((code[j].equals("int")) || code[j].equals("double")) {
                                if (j != 0) {
                                    LiteScipt.instance.consoleSender.call("org.litescipt.lang.RuntimeError: " + name +
                                            "\nat " + "org.litescipt.loader.Class.run()[Class:??]" +
                                            "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]");
                                    return;
                                }
                                if (funWorker == null) {
                                    LiteScipt.instance.consoleSender.call("org.litescipt.lang.NoMethodForVariableError: " + name
                                            + "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]" +
                                            "\nat " + name + ".null()[" + name + ".lsp:" + "]" +
                                            "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                    return;
                                }
                                if (code.length == 4) {
                                    if (j != 0) {
                                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.RuntimeError: " + name +
                                                "\nat " + "org.litescipt.loader.Class.run()[Class:??]" +
                                                "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]");
                                        return;
                                    }
                                    String name1 = code[1];
                                    String judge = code[2];
                                    String value = code[3];
                                    if (variableMap.containsKey(name1)) {
                                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.VariableExistsError: " + name
                                                + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                                "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                                "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                        return;
                                    }
                                    if (judge.equals("=") && !value.contains("+") && !value.contains("-") && !value.contains("*") && !value.contains("/")) {
                                        switch (code[j]) {
                                            case "int":
                                                Integer integer = new Integer(name1, java.lang.Integer.valueOf(value));
                                                this.variableMap.put(name1, integer);
                                                LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_INTEGER" + "[" + name1 + ":" + (i) + "]");
                                                break;
                                            case "double":
                                                org.litescipt.loader.lang.Double db = new org.litescipt.loader.lang.Double(name1, java.lang.Double.valueOf(value));
                                                this.variableMap.put(name1, db);
                                                LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_DOUBLE" + "[" + name1 + ":" + (i) + "]");
                                                break;
                                        }
                                    }
                                } else if (code.length == 2 && !string.contains("=")) {
                                    if (j != 0) {
                                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.RuntimeError: " + name +
                                                "\nat " + "org.litescipt.loader.Class.run()[Class:??]" +
                                                "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]");
                                        return;
                                    }
                                    String verCode = code[1];
                                    if (verCode.contains(",")) {
                                        String[] verCodes = verCode.split("\\,");
                                        for (String verEach : verCodes) {
                                            switch (code[j]) {
                                                case "int":
                                                    this.variableMap.put(verEach, new Integer(verEach, 0));
                                                    LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_INTEGER" + "[" + verEach + ":" + (i) + "]");
                                                    break;
                                                case "double":
                                                    this.variableMap.put(verEach, new org.litescipt.loader.lang.Double(verCode, 0D));
                                                    LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_DOUBLE" + "[" + verEach + ":" + (i) + "]");
                                                    break;
                                            }
                                        }
                                    } else {
                                        switch (code[j]) {
                                            case "int":
                                                this.variableMap.put(verCode, new Integer(verCode, 0));
                                                LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_INTEGER" + "[" + verCode + ":" + (i) + "]");
                                                break;
                                            case "double":
                                                this.variableMap.put(verCode, new org.litescipt.loader.lang.Double(verCode, 0D));
                                                LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_DOUBLE" + "[" + verCode + ":" + (i) + "]");
                                                break;
                                        }
                                    }
                                }
                            } else if (code[j].equals("import")) {
                                if (j != 0) {
                                    LiteScipt.instance.consoleSender.call("org.litescipt.lang.RuntimeError: " + name +
                                            "\nat " + "org.litescipt.loader.Class.run()[Class:??]" +
                                            "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]");
                                    return;
                                }
                                String targetClass = code[j + 1];
                                if (targetClass.contains("<") && targetClass.contains(">")) {
                                    if (funWorker == null) {
                                        ClassLoader classLoader = LiteScipt.instance.classLoader;
                                        targetClass = targetClass.replace("<", "");
                                        targetClass = targetClass.replace(">", "");
                                        if (!targetClass.equals(name)) {
                                            classLoader.load(targetClass);
                                            classLoader.onEnable(this.name);
                                            this.importClasses.put(targetClass, classLoader.classHashMap.get(targetClass));
                                        } else {
                                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + name
                                                    + "\nat " + name + "." + "null" + "()[" + name + ".lsp:" + (i) + "]" +
                                                    "\nat " + name + "." + "null" + "()[" + name + ".lsp:" + (i) + "]" +
                                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                            return;
                                        }
                                    } else {
                                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodClassDefNotFoundError: " + name
                                                + "\nat " + name + "." + "null" + "()[" + name + ".lsp:" + (i) + "]" +
                                                "\nat " + name + "." + "null" + "()[" + name + ".lsp:" + (i) + "]" +
                                                "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                        return;
                                    }
                                } else {
                                    LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodClassDefNotFoundError: " + name
                                            + "\nat " + name + "." + "null" + "()[" + name + ".lsp:" + (i) + "]" +
                                            "\nat " + name + "." + "null" + "()[" + name + ".lsp:" + (i) + "]" +
                                            "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                    return;
                                }
                            }
                            break;
                        case Symbol.TYPE_MATH:
                            LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_MATH:" + code[j]);
                            break;
                        case Symbol.TYPE_CHAR:
                            if (funWorker != null) {
                                if (code[j].equals("}")) {
                                    this.funEndLine.put(funWorker, i);
                                    //System.out.print("函数" + funWorker + "结尾行" + funEndLine.get(funWorker));
                                    LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_CHAR:" + funWorker + "[END:" + (i) + "]");
                                    ArrayList<String> codesOfMethod = read(this.getFunctionStartLine(funWorker), i);
                                    if (!funWorker.equals("main")) {
                                        ObjectMethod objectMethod = new ObjectMethod(this, funWorker, codesOfMethod, funTypes.get(funWorker), funArgs.get(funWorker));
                                        this.funObjects.put(funWorker, objectMethod);
                                    }
                                    //TODO: ObjectMethod
                                    funWorker = null;
                                }
                            } else {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + name
                                        + "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:79]" +
                                        "\nat " + name + ".null()");
                                return;
                            }
                        default:
                            if (code.length > 1) {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.UnknownMethodError: " + name
                                        + "\nat " + name + ".null()[" + name + ".lsp:" + (i) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:79]" +
                                        "\nat " + name + ".null()");
                                return;
                            }
                            break;
                    }
                }
            }
            i++;
        }

        if (!functionList.contains("main") || !funEndLine.containsKey("main")) {
            LiteScipt.instance.consoleSender.call("org.litescipt.lang.MainClassNotFoundError: " + name
                    + "\nat " + name + ".main(args)[" + name + ".lsp:" + "???" + "]" +
                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
            return;
        }
        String treeNode = "Parse Tree For [" + name + ".lsp] \nmain()\n|\n|\n---";
        for (String nodeTree : funCodeArrMap.keySet()) {
            if (!nodeTree.equals("main")) {
                treeNode += nodeTree + "---";
            }
        }
        LiteScipt.instance.consoleSender.debug(treeNode);
        this.enable();
    }

    public void enable() {
        int mainStart = getFunctionStartLine("main");
        int mainEnd = this.funEndLine.get("main");
        this.funCodeArrMap.put("main", read(mainStart, mainEnd));
        LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_FUN:" + "main()" + "[" + (mainStart) + "-" + (mainEnd) + "]");
        ObjectMethod objectMethod = new ObjectMethod(this, "main", funCodeArrMap.get("main"), this.funTypes.get("main"), this.funArgs.get("main"));
        this.funObjects.put("main", objectMethod);
        this.invoke("main", mainStart, funObjects.get("main"));
    }

    public int getFunctionStartLine(String fun) {
        int i = 0;
        for (String str : functionList) {
            if (str.equals(fun)) break;
            i++;
        }
        return functionCount.get(i);
    }

    public String deleteString2(String str, char delChar) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != delChar) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    public int getStringInStringIndex(String string, char target) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == target) return i;
        }
        return 0;
    }

    private void invoke(String fun, int start, ObjectMethod objectMethod) {
        ArrayList<String> funCode = funCodeArrMap.get(fun);
        this.funWorker = fun;
        int i = 1;
        String funVariable = objectMethod.getFunInvokeArgs().equals("void") ? " " : objectMethod.getFunInvokeArgs();
        String returnType = objectMethod.getFunReturnType();
        for (String str : funCode) {
            this.workLine = i;
            str = str.trim();
            String[] code = str.split("\\s+");
            String type = Symbol.getType(str);
            String cmd = code[0];
            if (type != null) {
                switch (type) {
                    case Symbol.TYPE_KEY:
                        if (cmd.equals("fun")) {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodRuntimeError: " + name
                                    + "\nat " + name + ".main(args)[" + name + ".lsp:" + (start + i) + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return;
                        }
                        if (cmd.contains("(") && cmd.contains(")")) {
                            String[] funArgs = cmd.split("\\(", 2);
                            switch (funArgs[0]) {
                                case "print":
                                    String mathCode = funArgs[1];
                                    if (mathCode.contains(")")) {
                                        mathCode += "true";
                                        mathCode = mathCode.split("\\)", 2)[0];
                                    }
                                    ObjectMath objectMath = new ObjectMath(this, mathCode, i);
                                    if (!objectMath.check(mathCode)) {
                                        if (this.variableMap.containsKey(mathCode)) {
                                            if (variableMap.get(mathCode) instanceof Integer) {
                                                LiteScipt.instance.consoleSender.call(String.valueOf(((Integer) variableMap.get(mathCode)).getValue()));
                                            } else if (variableMap.get(mathCode) instanceof org.litescipt.loader.lang.Double) {
                                                LiteScipt.instance.consoleSender.call(String.valueOf(((org.litescipt.loader.lang.Double) variableMap.get(mathCode)).getValue()));
                                            }
                                        }
                                    } else {
                                        double resultValueMath2 = objectMath.getDoubleValue();
                                        int resultValueMath3 = objectMath.getIntValue();
                                        if (resultValueMath2 != 0 || resultValueMath3 != 0) {
                                            if (resultValueMath2 != 0)
                                                LiteScipt.instance.consoleSender.call(String.valueOf(resultValueMath2));
                                            else
                                                LiteScipt.instance.consoleSender.call(String.valueOf(resultValueMath3));
                                        } else {
                                            LiteScipt.instance.consoleSender.call(String.valueOf(mathCode));
                                        }
                                    }
                                    break;
                                case "println":
                                    break;
                            }
                        } else if (Objects.equals(Symbol.getType(str.split("\\s+", 2)[1]), Symbol.TYPE_MATH)) {
                            if (code.length >= 4) {
                                String[] mathCode = str.split("\\s+", 4);
                                String mathType = mathCode[0];
                                if (mathType.equals("int") || mathType.equals("double")) {
                                    if (mathType.equals("int")) {
                                        String nameMath = mathCode[1];
                                        if (!mathCode[2].equals("=")) {
                                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodCharacterError: " + name
                                                    + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                                    "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + "]" +
                                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                            return;
                                        }
                                        String mathExpress = mathCode[3];
                                        ObjectMath objectMath = new ObjectMath(this, mathExpress, i);
                                        int mathValue = objectMath.getIntValue();
                                        this.variableMap.put(nameMath, new Integer(nameMath, mathValue));
                                        LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_INTEGER_DF" + "[" + nameMath + ":" + (i) + "]");
                                    } else {
                                        String nameMath = mathCode[1];
                                        if (!mathCode[2].equals("=")) {
                                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodCharacterError: " + name
                                                    + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                                    "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + "]" +
                                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                            return;
                                        }
                                        String mathExpress = mathCode[3];
                                        ObjectMath objectMath = new ObjectMath(this, mathExpress, i);
                                        double mathValue = objectMath.getDoubleValue();
                                        this.variableMap.put(nameMath, new org.litescipt.loader.lang.Double(nameMath, mathValue));
                                        LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_DOUBLE_DF" + "[" + nameMath + ":" + (i) + "]");
                                    }
                                }
                            }
                        }
                        ;
                    case Symbol.TYPE_MATH:
                        String[] mathStrings = str.split("\\s+", 3);
                        if (this.variableMap.containsKey(mathStrings[0])) {
                            if (this.variableMap.get(mathStrings[0]) instanceof Integer) {
                                int valueMath = (int) ((Integer) this.variableMap.get(mathStrings[0])).getValue();
                                if (!mathStrings[1].equals("=")) {
                                    LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodCharacterError: " + name
                                            + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                            "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + "]" +
                                            "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                    return;
                                }
                                String mathExpress = mathStrings[2];
                                ObjectMath objectMath = new ObjectMath(this, mathExpress, i);
                                int resultMathValue = objectMath.getIntValue();
                                this.variableMap.put(mathStrings[0], new Integer(mathStrings[0], resultMathValue));
                                LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_INTEGER_CG{" + valueMath + "->" + resultMathValue + "[" + mathStrings[0] + ":" + (i) + "]");
                            } else if (this.variableMap.get(mathStrings[0]) instanceof org.litescipt.loader.lang.Double) {
                                double valueMath = ((org.litescipt.loader.lang.Double) this.variableMap.get(mathStrings[0])).getValue();
                                if (!mathStrings[1].equals("=")) {
                                    LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodCharacterError: " + name
                                            + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                            "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + "]" +
                                            "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                    return;
                                }
                                String mathExpress = mathStrings[2];
                                ObjectMath objectMath = new ObjectMath(this, mathExpress, i);
                                double resultMathValue = objectMath.getDoubleValue();
                                this.variableMap.put(mathStrings[0], new org.litescipt.loader.lang.Double(mathStrings[0], resultMathValue));
                                LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "VARIABLE_DOUBLE_CG{" + valueMath + "->" + resultMathValue + "[" + mathStrings[0] + ":" + (i) + "]");
                            }
                        }
                        break;
                    default:
                        break;
                }
            } else {
                if (cmd.contains("(") && cmd.contains(")")) {
                    String funName = str;
                    String[] each = null;
                    String funToArgs = funName.split("\\(", 2)[1];
                    funName = funName.split("\\(", 2)[0];
                    if (funName.contains(")")) {
                        funName += "true";
                        funName = funName.split("\\)", 2)[0];
                    }
                    if (!funToArgs.equals(")")) {
                        if (funToArgs.contains(")")) {
                            funToArgs += "true";
                            funToArgs = funToArgs.split("\\)", 2)[0];
                        }
                        each = funToArgs.split("\\,");
                    }
                    if (this.functionList.contains(funName) && this.funEndLine.containsKey(funName)) {
                        ObjectMethod preMethodObject = this.funObjects.get(funName);
                        if (each != null) {
                            ObjectMethod invokeMethod = this.funObjects.get(funName);
                            if (each.length != invokeMethod.getArgsSize()) {
                                LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodInvokeError: " + name
                                        + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                        "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                                return;
                            }
                            ArrayList<String>  argsArrayList = new ArrayList<>(Arrays.asList(each));
                            preMethodObject.update(argsArrayList);
                        }
                        int startLineFun = this.getFunctionStartLine(funName);
                        int endLineFun = this.funEndLine.get(funName);
                        this.funCodeArrMap.put(funName, read(startLineFun, endLineFun));
                        String oldWorker = funWorker;
                        LiteScipt.instance.consoleSender.debug("语法分析: SYMBOL_TYPE:" + "INVOKE_FUN" + funName + "}[" + name + ":" + (i) + "]");
                        this.funWorker = funName;
                        invoke(funName, startLineFun, preMethodObject);
                        this.funWorker = oldWorker;
                    } else {
                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + name
                                + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + "]" +
                                "\nat " + name + "." + funName + "()[" + name + ".lsp:" + (i) + "]" +
                                "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                        return;
                    }
                } else if (isVariable(cmd)) {
                    if (code.length == 3) {
                        if (!code[1].equals("=")) {
                            LiteScipt.instance.consoleSender.call("org.litescipt.lang.MethodCharacterError: " + name
                                    + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                    "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + "]" +
                                    "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                            return;
                        }
                        String mathPress = code[2];
                        ObjectMath objectMath = new ObjectMath(this, mathPress, i);
                        if (this.variableMap.containsKey(cmd) && this.variableMap.get(cmd) instanceof Integer) {
                            this.variableMap.put(cmd, new Integer(cmd, objectMath.getIntValue()));
                        } else if (this.variableMap.containsKey(cmd) && this.variableMap.get(cmd) instanceof org.litescipt.loader.lang.Double) {
                            this.variableMap.put(cmd, new org.litescipt.loader.lang.Double(cmd, objectMath.getDoubleValue()));
                        }
                    } else {
                        LiteScipt.instance.consoleSender.call("org.litescipt.lang.NullPointerError: " + name
                                + "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + "]" +
                                "\nat " + name + "." + funWorker + "()[" + name + ".lsp:" + (i) + "]" +
                                "\nat " + "org.litescipt.loader.Class.run()[Class:57]");
                        return;
                    }
                }
            }

            i++;
        }
    }

    private boolean isVariable(String name) {
        if (this.variableMap.containsKey(name)) return true;
        else return false;
    }

    public ArrayList<String> read(int start, int end) {
        try {
            ArrayList<String> funCode = new ArrayList<>();
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            int i = 1;
            while ((s = br.readLine()) != null) {
                if (i > start && i < end) {
                    LiteScipt.instance.consoleSender.debug("语法分析: INVOKE_FUN" + "()" + "{" + s + "}" + "[" + (start) + "-" + (end) + "]");
                    funCode.add(s);
                }
                i++;
            }
            br.close();
            return funCode;
        } catch (IOException e) {
            LiteScipt.instance.consoleSender.call("org.litescipt.lang.NoClassDefFoundError: " + path
                    + "\nat " + path + ".main(args)[" + path + ".lsp:???]");
        }
        return null;
    }


}
