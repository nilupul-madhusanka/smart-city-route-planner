package data;

public class LocationTree {
    private AVLNode root;

    /*Height helper for AVL operations.*/
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private AVLNode insert(AVLNode node, String key) {
        if (node == null) return new AVLNode(key);
        if (key.compareTo(node.key) < 0) node.left = insert(node.left, key);
        else if (key.compareTo(node.key) > 0) node.right = insert(node.right, key);
        else return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0) return rotateRight(node);
        if (balance < -1 && key.compareTo(node.right.key) > 0) return rotateLeft(node);
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    /*Public insert with basic validationPublic insert with basic validation.*/
    public void insert(String key) {
        if (key == null) return;
        String k = key.trim();
        if (k.isEmpty()) return;
        root = insert(root, k);
    }

    public void displayInOrder() {
        System.out.println("\n--- All Locations ---");
        if (root == null) {
            System.out.println("No locations added yet.");
            return;
        }
        inOrder(root);
        System.out.println();
    }

    private void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }


    public boolean isEmpty() {
        return root == null;
    }


    /*Remove a key from the tree if it exists.*/
    public boolean remove(String key) {
        if (key == null) return false;
        String k = key.trim();
        if (k.isEmpty()) return false;
        boolean existed = contains(root, k);
        root = deleteNode(root, k);
        return existed;
    }

    /*Check if the tree contains a key.*/
    public boolean contains(String key) {
        if (key == null) return false;
        String k = key.trim();
        if (k.isEmpty()) return false;
        return contains(root, k);
    }

    private boolean contains(AVLNode node, String key) {
        if (node == null) return false;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return true;
        if (cmp < 0) return contains(node.left, key);
        return contains(node.right, key);
    }

    private AVLNode deleteNode(AVLNode node, String key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = deleteNode(node.left, key);
        } else if (cmp > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            // node to be deleted
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                // Two children: get inorder successor
                AVLNode successor = minValueNode(node.right);
                node.key = successor.key;
                node.right = deleteNode(node.right, successor.key);
            }
        }

        if (node == null) return null;

        // Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Rebalance
        int balance = getBalance(node);

        // Left heavy
        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                return rotateRight(node); // LL
            } else {
                node.left = rotateLeft(node.left); // LR
                return rotateRight(node);
            }
        }

        // Right heavy
        if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                return rotateLeft(node); // RR
            } else {
                node.right = rotateRight(node.right); // RL
                return rotateLeft(node);
            }
        }

        return node;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }
}