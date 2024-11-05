package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BinarySearchTreeTest {

    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.put("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7 }) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenAddDuplicateThenReturnFalse() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThat(tree.put(1)).isTrue();
        assertThat(tree.put(1)).isFalse();  // Duplicate element
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly(1);
    }
    @Test
    void whenRemoveLeafNodeThenNodeIsRemoved() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(5);
        tree.put(3);
        tree.put(7);
        assertThat(tree.remove(3)).isTrue(); // Removing leaf node
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly(5, 7);
    }

    @Test
    void whenRemoveNodeWithOneChildThenNodeIsRemoved() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(5);
        tree.put(3);
        tree.put(2);
        assertThat(tree.remove(3)).isTrue(); // Removing node with one child
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly(2, 5);
    }

    @Test
    void whenRemoveNodeWithTwoChildrenThenNodeIsReplacedWithHeir() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(5);
        tree.put(3);
        tree.put(7);
        tree.put(2);
        tree.put(4);
        assertThat(tree.remove(3)).isTrue(); // Removing node with two children
        assertThat(tree.inSymmetricalOrder()).hasSize(4)
                .containsExactly(2, 4, 5, 7);
    }

    @Test
    void whenRemoveRootWithTwoChildrenThenRootIsReplaced() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(5);
        tree.put(3);
        tree.put(7);
        tree.put(2);
        tree.put(4);
        assertThat(tree.remove(5)).isTrue(); // Removing root with two children
        assertThat(tree.inSymmetricalOrder()).hasSize(4)
                .containsExactly(2, 3, 4, 7);
    }

    @Test
    void whenRemoveNonExistentElementThenReturnFalse() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(5);
        tree.put(3);
        tree.put(7);
        assertThat(tree.remove(10)).isFalse(); // Element does not exist
    }

    @Test
    void whenTreeIsEmptyThenMinimumAndMaximumAreNull() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThat(tree.minimum()).isNull();
        assertThat(tree.maximum()).isNull();
    }

    @Test
    void whenAddElementsThenMinimumAndMaximumReturnCorrectValues() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(5);
        tree.put(3);
        tree.put(7);
        tree.put(2);
        tree.put(4);
        assertThat(tree.minimum()).isEqualTo(2);
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenInSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenInPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenInPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenTreeContainsElementThenReturnTrue() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenRemoveRootOnlyNodeThenTreeIsEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(10);
        assertThat(tree.remove(10)).isTrue();
        assertThat(tree.inSymmetricalOrder()).isEmpty();
    }

    @Test
    void whenRemoveRootWithLeftChildOnlyThenLeftChildBecomesRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(10);
        tree.put(5);
        assertThat(tree.remove(10)).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly(5);
    }

    @Test
    void whenRemoveRootWithRightChildOnlyThenRightChildBecomesRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(10);
        tree.put(15);
        assertThat(tree.remove(10)).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly(15);
    }
}