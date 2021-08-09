package org.litescipt.loader.structure;

import java.util.ArrayList;
import java.util.List;

public class StructNode<T> {

    public T t;
    private StructTree<T> parent;

    public List<StructNode<T>> nodelist;

    public StructNode(T stype) {
        t = stype;
        parent = null;
        nodelist = new ArrayList<StructNode<T>>();
    }

    public StructTree<T> getParent() {
        return parent;
    }

}
