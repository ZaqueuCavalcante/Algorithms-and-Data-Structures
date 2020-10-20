package com.zaqueu;

import com.zaqueu.lists.DoublyLinkedList;

public class Main {

    public static void main(String[] args) {
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        dll.addFirst("Z");
        dll.addLast("A");
        dll.addLast("Q");
        dll.addLast("U");
        dll.addLast("E");
        dll.addLast("U");
        System.out.println(dll);
        dll.addFirst("?");
        System.out.println(dll);
        System.out.println(dll.contains("Z"));
        dll.addAt(2, "4");
        System.out.println(dll);
        System.out.println("Head data: " + dll.headData());
        System.out.println("Tail data: " + dll.tailData());
        dll.removeAt(0);
        System.out.println(dll);
        dll.clear();
        System.out.println(dll);
    }
    
}
