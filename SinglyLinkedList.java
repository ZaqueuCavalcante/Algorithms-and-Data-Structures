package com.zaqueu.lists;

public class SinglyLinkedList<T> {
    private int size = 0;
    private Node<T> head = null;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public void clear() {
            data = null;
            next = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public void addFirst(T data) { // O(1)
        if (isEmpty()) {
            head = new Node<>(data, null);
        } else {
            Node<T> newHead = new Node<>(data, head);
            head = newHead;
        }
        size++;
    }

    public void addAfter(Node<T> previousNode, T data) { // O(1)
        Node<T> newNode = new Node<>(data, previousNode.next);
        previousNode.next = newNode;
        size++;
    }

    public void append(T data) { // O(n)
        if (head == null) {
            head = new Node<>(data, null);
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(data, null);
        size++;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    private void checkIndexOutOfRangeException(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of range.");
        }
    }

    private Node<T> getNodeAt(int index) { // O(n)
        checkIndexOutOfRangeException(index);
        Node<T> current = head;
        for (int i = 0; i != index; i++) {
            current = current.next;
        }
        return current;
    }

    public int indexOf(Object obj) { // O(n)
        int index = 0;
        Node<T> current = head;
        if (obj == null) {
            while (current != null) {
                if (current.data == null) { return index; }
                current = current.next;
                index++;
            }
        } else {
            while (current != null) {
                if (obj.equals(current.data)) { return index; }
                current = current.next;
                index++;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) { // O(n)
        return indexOf(obj) != -1;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    private void checkEmptyListException() {
        if (isEmpty()) throw new RuntimeException("Empty list.");
    }

    public T headData() { // O(1)
        checkEmptyListException();
        return head.data;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public T removeFirst() { // O(1)
        checkEmptyListException();

        T data = head.data;
        head = head.next;
        --size;

        return data;
    }

    private T remove(Node<T> node) { // O(1)
        Node<T> rightNode = node.next;

        T data = node.data;
        node.clear();
        --size;

        return data;
    }

    public T removeAt(int index) { // O(n)
        Node<T> current = getNodeAt(index);
        return remove(current);
    }

    public boolean remove(Object obj) { // O(n)
        int index = indexOf(obj);
        if (index >= 0) {
            removeAt(index);
            return true;
        }
        return false;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public void clear() { // O(n)
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.clear();
            current = next;
        }
        head = null;
        size = 0;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        if (!isEmpty()) sb.append(" -> null");
        sb.append(" ]");
        return sb.toString();
    }

}
