package org.litescipt.loader.structure;

public class StructTree<T> {

    public StructNode<T> root;

    public StructTree() {
    }

    public void addNode(StructNode<T> node, T newNode) {
        //增加根节点
        if (null == node) {
            if (null == root) {
                root = new StructNode(newNode);
            }
        } else {
            StructNode<T> temp = new StructNode(newNode);
            node.nodelist.add(temp);
        }
    }

    /*    查找newNode这个节点 */
    public StructNode<T> search(StructNode<T> input, T newNode) {

        StructNode<T> temp = null;

        if (input.t.equals(newNode)) {
            return input;
        }

        for (int i = 0; i < input.nodelist.size(); i++) {

            temp = search(input.nodelist.get(i), newNode);

            if (null != temp) {
                break;
            }
        }

        return temp;
    }

    public StructNode<T> getNode(T newNode) {
        return search(root, newNode);
    }

    public void showNode(StructNode<T> node) {
        if (null != node) {
            //循环遍历node的节点
            System.out.println(node.t.toString());
            for (int i = 0; i < node.nodelist.size(); i++) {
                showNode(node.nodelist.get(i));
            }
        }
    }

}
