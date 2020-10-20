package com.zaqueu.lists;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    private static class Node<T> {
        private T data;
        private Node<T> previous, next;

        public Node(T data, Node<T> previous, Node<T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public void clear() {
            data = null;
            previous = null;
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
        return size() == 0;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public void addFirst(T data) { // O(1)
        if (isEmpty()) {
            head = tail = new Node<>(data, null, null);
        } else {
            head.previous = new Node<>(data, null, head);
            head = head.previous;
        }
        size++;
    }

    public void addLast(T data) { // O(1)
        if (isEmpty()) {
            head = tail = new Node<>(data, null, null);
        } else {
            tail.next = new Node<>(data, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void addAt(int index, T data) { // O(n)
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index out of range.");
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node<T> leftNode = getNodeAt(index - 1);
        Node<T> rightNode = leftNode.next;
        Node<T> newNode = new Node<>(data, leftNode, rightNode);
        leftNode.next = rightNode.previous = newNode;
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
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i != index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i != index; i--) {
                current = current.previous;
            }
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

    public T tailData() { // O(1)
        checkEmptyListException();
        return tail.data;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public T removeFirst() { // O(1)
        checkEmptyListException();

        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty()) tail = null;
        else head.previous = null;

        return data;
    }

    public T removeLast() { // O(1)
        checkEmptyListException();

        T data = tail.data;
        tail = tail.previous;
        --size;

        if (isEmpty()) head = null;
        else tail.next = null;

        return data;
    }

    private T remove(Node<T> node) { // O(1)
        Node<T> leftNode = node.previous;
        Node<T> rightNode = node.next;

        if (leftNode == null) return removeFirst();
        if (rightNode == null) return removeLast();

        leftNode.next = rightNode;
        rightNode.previous = leftNode;

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
        head = tail = null;
        size = 0;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (!isEmpty()) sb.append("null <- ");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" <-> ");
            }
            current = current.next;
        }
        if (!isEmpty()) sb.append(" -> null");
        sb.append(" ]");
        return sb.toString();
    }

}
