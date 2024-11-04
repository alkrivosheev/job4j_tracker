package ru.job4j.newcoll.tree;
import ru.job4j.newcoll.SimpleQueue;
import ru.job4j.newcoll.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root node cannot be null");
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            if (current.getValue().equals(key)) {
                return Optional.of(current);
            }
            for (Node node : current.getChildren()) {
                stack.push(node);
            }
        }
        return Optional.empty();
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException("Root node cannot be null");
        }
        Optional<Node<T>> parentNodeOpt = findByKey(root, parent);
        if (parentNodeOpt.isEmpty()) {
            return false;
        }
        Node<T> parentNode = parentNodeOpt.get();
        if (findByKey(root, child).isPresent()) {
            return false;
        }
        Node<T> childNode = new Node<>(child);
        parentNode.getChildren().add(childNode);
        return true;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root node cannot be null");
        }
        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            List<Node<T>> children = currentNode.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Node<T> child = children.get(i);
                if (child.getValue().equals(key)) {
                    children.remove(i);
                    return Optional.of(child);
                }
                stack.push(child);
            }
        }
        return Optional.empty();
    }
}