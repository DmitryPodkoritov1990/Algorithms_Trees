import org.w3c.dom.Node;

import java.awt.*;


public class RedBlackTree {

    private Node root;

    public  boolean add(int value){
        if (root != null){
            boolean result = addNode(root, value);
            root = rebalnce(root);
            root.color = Color.BLACK;
            return result;
        }else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    private boolean addNode(Node node, int value){
        if(node.value == value){
            return false;
        }else {
            if (node.value > value){
                if(node.leftchild != null){
                    boolean result = addNode(node.leftchild, value);
                    node.leftchild = rebalnce(node.leftchild);
                    return result;
                } else {
                    node.leftchild = new Node();
                    node.leftchild.color = Color.RED;
                    node.leftchild.value = value;
                    return true;
                }
            }else {
                if (node.rightchild != null){
                    boolean result = addNode(node.rightchild, value);
                    node.rightchild = rebalnce(node.rightchild);
                    return result;
                }else {
                    node.rightchild = new Node();
                    node.rightchild.color = Color.RED;
                    node.rightchild.value = value;
                    return true;
                }
            }
        }
    }
    private Node rebalnce(Node node) {
        Node result = node;
        boolean needRebalnce;
        do {
            needRebalnce = false;
            if (result.rightchild != null && result.rightchild.color == Color.RED &&
                    (result.leftchild == null || result.leftchild.color == Color.BLACK)) {
                needRebalnce = true;
                result = rightSwap(result);
            }
            if (result.leftchild != null && result.leftchild.color == Color.RED &&
                    (result.leftchild.leftchild == null && result.leftchild.leftchild.color == Color.RED)) {
                needRebalnce = true;
                result = leftSwap(result);
            }
            if (result.leftchild != null && result.leftchild.color == Color.RED &&
                    (result.rightchild != null && result.rightchild.color == Color.RED)){
               needRebalnce = true;
               colorSwap(result);
            }
        }
        while (needRebalnce);
        return result;
    }
    private Node rightSwap(Node node){
        Node rightchild = node.rightchild;
        Node betweenchild = rightchild.leftchild;
        rightchild.leftchild = node;
        node.rightchild = betweenchild;
        rightchild.color = node.color;
        node.color = Color.RED;
        return rightchild;
    }
    private Node leftSwap(Node node){
        Node leftchild = node.leftchild;
        Node betweenchild = leftchild.rightchild;
        leftchild.rightchild = node;
        node.leftchild = betweenchild;
        leftchild.color = node.color;
        node.color = Color.RED;
        return leftchild;
    }
    private void colorSwap(Node node){
        node.rightchild.color = Color.BLACK;
        node.leftchild.color = Color.BLACK;
        node.color = Color.RED;
    }

    private class Node {
        private int value;
        private Color color;
        private Node leftchild;
        private Node rightchild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    '}';
        }
    }
    private enum Color {
        RED, BLACK
    }
}

