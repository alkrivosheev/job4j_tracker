package ru.job4j.newcoll.tree;
import ru.job4j.newcoll.SimpleQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root node cannot be null");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        int count = 0;
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            count++;
            if (current != null) {
                for (Node node : current.getChildren()) {
                    queue.push(node);
                }
            }
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root node cannot be null");
        }
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> result = new ArrayList<>();
        queue.push(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            result.add(current.getValue());
            if (current != null) {
                for (Node node : current.getChildren()) {
                    queue.push(node);
                }
            }
        }
        return result;
    }
}