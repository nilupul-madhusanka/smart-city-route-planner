package data;

public class AVLNode {
    String key;
    AVLNode left, right;
    int height;

    public AVLNode(String key) {
        this.key = key;
        this.height = 1;
    }
}