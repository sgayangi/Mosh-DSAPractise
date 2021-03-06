package src;

import java.util.ArrayList;

public class TreePractise {
    public static void main(String[] args) {
        Tree tree1 = new Tree();
        tree1.insertChild(10);
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

//        System.out.println(tree1.equalTree(null));
        tree1.swapRoot();
        System.out.println(tree1.isTreeBinarySearchTree());
        tree1.getNodesAtKDistance(4);
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
        if (tree == null || tree.root == null) {
            throw new IllegalArgumentException("Cannot compare empty tree/null tree");
        }
        return isEqualNode(root, tree.root);
    }

    public void swapRoot() {
        var temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
    }

    private boolean isEqualNode(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 != null && root2 != null) {
            return root1.value == root2.value && isEqualNode(root1.leftChild, root2.leftChild) && isEqualNode(root1.rightChild, root2.rightChild);
        }
        return false;
    }

    public boolean isTreeBinarySearchTree() {
        if (root == null) throw new IllegalStateException();
        return isTreeBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isTreeBinarySearchTree(Node root, int start, int end) {
        if (root == null) return true;
        if (root.value > start && root.value < end) {
            return isTreeBinarySearchTree(root.leftChild, start, root.value) && isTreeBinarySearchTree(root.rightChild, root.value, end);
        } else {
            return false;
        }
    }

    public void getNodesAtKDistance(int distance) {
        ArrayList<Integer> nodes = new ArrayList<>();
        getNodesAtKDistance(root, distance, nodes);
        
    }

    private void getNodesAtKDistance(Node root, int distance, ArrayList<Integer> nodes) {
        if (root == null) return;
        if (distance == 0) {
            nodes.add(root.value);
            System.out.println(root.value);
            return;
        }
        getNodesAtKDistance(root.leftChild, distance - 1, nodes);
        getNodesAtKDistance(root.rightChild, distance - 1, nodes);

    }

}

