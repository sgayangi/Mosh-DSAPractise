package src;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class linkedlistpractise {
    public static void main(String[] args) {
        LinkedListImplementation linkedList = new LinkedListImplementation();
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addFirst(30);
        System.out.println(linkedList.indexOf(20));
        System.out.println(linkedList.contains(100));
        System.out.println(linkedList.contains(10));
        System.out.println(linkedList.size());
        System.out.println(Arrays.toString(linkedList.toArray()));
    }
}


class LinkedListImplementation {
    private Node first;
    private Node last;
    private int size;

    public void addFirst(int value) {
        var newNode = new Node(value);
        if (isEmpty()) {
            this.first = this.last = newNode;
        } else {
            newNode.next = first;
            this.first = newNode;
        }
        size++;
    }

    private boolean isEmpty() {
        return first == null;
    }

    public void addLast(int value) {
        var newNode = new Node(value);
        if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
        size++;

    }

    public void removeFirst(int value) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first == last) {
            first = last = null;
        } else {
            var second = first.next;
            first.next = null;
            first = second;
            size--;
        }
    }

    public void removeLast(int value) {
        if (isEmpty()) throw new NoSuchElementException();
        if (first == last) {
            first = last = null;
            return;
        }
        last = getPrevious(last);
        assert last != null;
        last.next = null;
        size--;
    }

    private Node getPrevious(Node node) {
        var current = this.first;
        while (current != null) {
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(int value) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
            index += 1;
        }
        return false;
    }

    public int indexOf(int value) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.value == value) {
                return index;
            }
            current = current.next;
            index += 1;
        }

        return -1;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {
        int[] array = new int[size];
        var current = first;
        int index = 0;
        while (current != null) {
            array[index] = current.value;
            index++;
            current = current.next;
        }

        return array;
    }

    class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}