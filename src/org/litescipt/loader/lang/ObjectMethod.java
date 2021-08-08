package org.litescipt.loader.lang;

import org.litescipt.loader.Class;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectMethod extends Object {

    private String funName;
    private ArrayList<String> funBuffer;

    private String funReturnType;//函数返回类型
    private String funInvokeArgs;//函数参数
    public String funReturnString;
    public HashMap<String, Object> argsValue;

    public ObjectMethod(Class aClass, String name, ArrayList<String> buffer, String funReturnType, String funInvokeArgs) {
        this.funName = name;
        this.funBuffer = new ArrayList<>();
        this.funBuffer = buffer;
        this.funReturnType = funReturnType;
        this.funInvokeArgs = funInvokeArgs;
        this.aClass = aClass;
        this.funReturnString = "void";
    }

    public void update(ArrayList<String> argsValue) {
        this.argsValue = new HashMap<>();
        String[] args = funInvokeArgs.split(",");
        int i = 0;
        for (String str : argsValue) {
            String variableType = args[i].split("\\s+")[0];
            String variableName = args[i].split("\\s+")[1];
            ObjectMath objectMath = new ObjectMath(aClass, str, aClass.workLine, this);
            if (objectMath.check(str)) {
                switch (variableType) {
                    case "int":
                        int value = objectMath.getIntValue();
                        this.argsValue.put(variableName, new Integer(variableName, value));
                        break;
                    case "double":
                        double valueD = objectMath.getDoubleValue();
                        this.argsValue.put(variableName, new Double(variableName, valueD));
                        break;
                }
            } else {
                if (aClass.variableMap.containsKey(str)) {
                    this.argsValue.put(variableName, aClass.variableMap.get(str));
                } else {
                    switch (variableType) {
                        case "int":
                            this.argsValue.put(variableName, new Integer(variableName, java.lang.Integer.valueOf(str)));
                            break;
                        case "double":
                            this.argsValue.put(variableName, new Double(variableName, java.lang.Double.valueOf(str)));
                            break;
                    }
                }
            }
            i++;
        }
    }

    public int getArgsSize() {
        return this.funInvokeArgs.split(",").length;
    }

    public String getFunName() {
        return this.funName;
    }

    public ArrayList<String> getFunBuffer() {
        return this.funBuffer;
    }

    public String getFunReturnType() {
        return this.funReturnType;
    }

    public String getFunInvokeArgs() {
        return this.funInvokeArgs;
    }


}
