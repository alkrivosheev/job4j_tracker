package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AvlTreeTest {

    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void preOrderTraversalIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inPreOrder();
        assertThat(list).containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void postOrderTraversalIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inPostOrder();
        assertThat(list).containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void minimumIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 3; i <= 10; i++) {
            tree.insert(i);
        }
        Integer min = tree.minimum();
        assertThat(min).isEqualTo(3);
    }

    @Test
    void maximumIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 3; i <= 10; i++) {
            tree.insert(i);
        }
        Integer max = tree.maximum();
        assertThat(max).isEqualTo(10);
    }

    @Test
    void insertionBalancesTreeCorrectly() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        List<Integer> inOrderList = tree.inSymmetricalOrder();
        assertThat(inOrderList).containsExactly(1, 2, 3);
        List<Integer> preOrderList = tree.inPreOrder();
        assertThat(preOrderList).containsExactly(2, 1, 3);
    }

    @Test
    void deletionBalancesTreeCorrectly() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.remove(4);
        List<Integer> inOrderList = tree.inSymmetricalOrder();
        assertThat(inOrderList).containsExactly(1, 2, 3, 5, 6, 7);
        List<Integer> preOrderList = tree.inPreOrder();
        assertThat(preOrderList).containsExactly(5, 2, 1, 3, 6, 7);
    }

    @Test
    void containsWorksCorrectly() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        assertThat(tree.contains(tree.root, 20)).isTrue();
        assertThat(tree.contains(tree.root, 40)).isFalse();
    }

    @Test
    void deleteLeafNode() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.remove(5);
        List<Integer> inOrderList = tree.inSymmetricalOrder();
        assertThat(inOrderList).containsExactly(10, 15);
    }

    @Test
    void deleteNodeWithOneChild() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12);
        tree.remove(15);
        List<Integer> inOrderList = tree.inSymmetricalOrder();
        assertThat(inOrderList).containsExactly(5, 10, 12);
    }

    @Test
    void deleteNodeWithTwoChildren() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.remove(10);
        List<Integer> inOrderList = tree.inSymmetricalOrder();
        assertThat(inOrderList).containsExactly(5, 15, 20, 30);
    }
}