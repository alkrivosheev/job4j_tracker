package ru.job4j.algo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node<Worker> {
    private Worker value;
    private List<Node<Worker>> children = new ArrayList<>();

    public Worker getValue() {
        return value;
    }

    public Node(Worker value) {
        this.value = value;
    }

    public List<Node<Worker>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<Worker>> children) {
        this.children = children;
    }

    @SafeVarargs
    public Node(Worker value, Node<Worker>... children) {
        this.value = value;
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public String toString() {
        return String.format("Node{ %s }", value);
    }
}