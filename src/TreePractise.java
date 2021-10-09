package src;

public class TreePractise {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insertChild(7);
        tree.insertChild(4);
        tree.insertChild(9);
        tree.insertChild(1);
        tree.insertChild(6);

        System.out.println(tree.find(10));
        System.out.println(tree.find(1));
    }
}

class Tree {
    private Node root;

    public void insertChild(int value) {
        if (this.root == null) {
            root = new Node(value);
        } else {
            var current = root;

            while (true) {
                Node newNode = new Node(value);
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

}

