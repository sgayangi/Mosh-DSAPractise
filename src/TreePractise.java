package src;

public class TreePractise {
    public static void main(String[] args) {
        Tree tree1 = new Tree();
        tree1.insertChild(7);
        tree1.insertChild(4);
        tree1.insertChild(9);
        tree1.insertChild(2);
        tree1.insertChild(6);
        tree1.insertChild(8);
        tree1.insertChild(10);

//        System.out.println(tree1.find(10));
//        System.out.println(tree1.find(1));
//        tree1.preOrderTraversal();
//        System.out.println("=======================");
//        tree1.postOrderTraversal();
//        System.out.println("=======================");
//        tree1.inOrderTraversal();
//        System.out.println("=======================");
//        System.out.println(tree1.calculateHeight());
//        System.out.println("=======================");
//        System.out.println(tree1.min());

        Tree tree2 = new Tree();
        tree2.insertChild(8);
        tree2.insertChild(4);
        tree2.insertChild(9);
        tree2.insertChild(2);
        tree2.insertChild(6);
        tree2.insertChild(8);
        tree2.insertChild(10);

        System.out.println(tree1.equalTree(tree2));
    }
}

//can be visited BFS or DFS
//if DFS, 3 types
// 1. inorder - sorted - can pick asc or desc order
// 2. preorder
// 3. postorder - visits leaves first then goes up towards the roots
class Tree {
    private Node root;

    public void insertChild(int value) {
        Node newNode = new Node(value);

        if (this.root == null) {
            root = newNode;
            return;
        }
        var current = root;
        while (true) {
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = newNode;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    current.rightChild = newNode;
                    break;
                }
                current = current.rightChild;
            }
        }

    }

    boolean find(int value) {
        var current = root;
        while (current != null) {
            if (value < current.value) {
                current = current.leftChild;
            } else if (value > current.value) {
                current = current.rightChild;
            } else {
                return true;
            }
        }
        return false;
    }

    private class Node {
        int value;
        Node leftChild;
        Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "NODE = " + value;
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        preOrderTraversal(root.leftChild);
        preOrderTraversal(root.rightChild);
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.leftChild);
        postOrderTraversal(root.rightChild);
        System.out.println(root.value);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.rightChild);
        System.out.println(root.value);
        inOrderTraversal(root.leftChild);
    }

    public int calculateHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(Node root) {
        if (root == null) {
            return -1;
        }
        if (isLeaf(root)) {
            return 0;
        }
        return 1 + Math.max(calculateHeight(root.leftChild), calculateHeight(root.rightChild));
    }

    public int minBinarySearchTree() {
        var current = root;
        var last = root;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last.value;
    }

    public int maxBinarySearchTree() {
        var current = root;
        var last = root;
        while (current != null) {
            last = current;
            current = current.rightChild;
        }
        return last.value;
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        if (root == null) {
            return (int) Double.POSITIVE_INFINITY;
        }
        if (isLeaf(root)) {
            return root.value;
        }
        var left = min(root.leftChild);
        var right = min(root.rightChild);

        return Math.min(Math.min(left, right), root.value);
    }

    private boolean isLeaf(Node node) {
        return (root.leftChild == null && root.rightChild == null);
    }

    public boolean equalTree(Tree tree) {
        if (root == null && tree.root == null) {
            return true;
        }
        if (tree.root == null) {
            throw new IllegalArgumentException("Cannot compare empty tree");
        }
        if (isEqualNode(root, tree.root)) {
            return true;
        }
        return false;
    }

    private boolean isEqualNode(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (isLeaf(root1) && (isLeaf(root2))) {
            return root1.value == root2.value;
        }
        return isEqualNode(root1.leftChild, root2.leftChild) && isEqualNode(root1.rightChild, root2.rightChild);

    }

}

