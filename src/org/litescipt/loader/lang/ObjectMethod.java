package org.litescipt.loader.lang;

import java.util.ArrayList;

public class ObjectMethod extends Object {

    private String funName;
    private ArrayList<String> funBuffer;

    private String funReturnType;//函数返回类型
    private String funInvokeArgs;//函数参数

    public ObjectMethod(String name, ArrayList<String> buffer, String funReturnType, String funInvokeArgs) {
        this.funName = name;
        this.funBuffer = new ArrayList<>();
        this.funBuffer = buffer;
        this.funReturnType = funReturnType;
        this.funInvokeArgs = funInvokeArgs;
    }

    public void update() {

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
