package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        boolean[] isNewEntry = {true};
        root = put(root, key, value, isNewEntry);
        return isNewEntry[0];
    }

    public boolean remove(T key) {
        boolean[] removed = {false};
        root = remove(root, key, removed);
        return removed[0];
    }

    public V get(T key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public Set<T> keySet() {
        Set<T> keys = new LinkedHashSet<>();
        inOrderKeys(root, keys);
        return keys;
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        inOrderValues(root, values);
        return values;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node put(Node node, T key, V value, boolean[] updated) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value, updated);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value, updated);
        } else {
            node.value = value;
            updated[0] = true;
            return node;
        }
        update(node);
        return balance(node);
    }

    private Node remove(Node node, T key, boolean[] removed) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key, removed);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key, removed);
        } else {
            removed[0] = true;
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = remove(node.right, successor.key, removed);
        }
        update(node);
        return balance(node);
    }

    private Node getNode(Node node, T key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node balance(Node node) {
        if (node.balanceFactor > 1) {
            if (node.left.balanceFactor < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (node.balanceFactor < -1) {
            if (node.right.balanceFactor > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        update(node);
        update(newRoot);
        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        update(node);
        update(newRoot);
        return newRoot;
    }

    private void update(Node node) {
        int leftHeight = (node.left == null) ? 0 : node.left.height;
        int rightHeight = (node.right == null) ? 0 : node.right.height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
        node.balanceFactor = leftHeight - rightHeight;
    }

    private void inOrderKeys(Node node, Set<T> keys) {
        if (node != null) {
            inOrderKeys(node.left, keys);
            keys.add(node.key);
            inOrderKeys(node.right, keys);
        }
    }

    private void inOrderValues(Node node, List<V> values) {
        if (node != null) {
            inOrderValues(node.left, values);
            values.add(node.value);
            inOrderValues(node.right, values);
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

}
