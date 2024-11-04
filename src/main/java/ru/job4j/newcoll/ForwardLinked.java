package ru.job4j.newcoll;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (size == 0) {
            head = newNode;
        } else {
            Node<T> node = head;
            for (int i = 1; i < size; i++) {
                node = node.next;
            }
            node.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deletedNode = head.item;
        Node<T> nextNode = head.next;
        head.item = null;
        head.next = null;
        head = nextNode;
        size--;
        modCount++;
        return deletedNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            final  int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
