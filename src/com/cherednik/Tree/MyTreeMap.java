package com.cherednik.Tree;

import java.util.LinkedHashSet;
import java.util.Set;

public class MyTreeMap<K extends Comparable<? super K>, V> implements MyMap<K, V> {
    private int size;
    private Node<K, V> root;

    public static class Node<K, V> implements MyMap.Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;
        private int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return findEntry(key) != null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = findEntry(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return null;
        }
        Node<K, V> current = root, prev = null;
        boolean prevLeft = false;
        while (current != null) {
            int comparison = ((Comparable<? super K>) key).compareTo(current.key);
            prev = current;
            if (comparison == 0) {
                V result = current.value;
                current.value = value;
                return result;
            } else if (comparison < 0) {
                current = current.left;
                prevLeft = true;
            } else {
                current = current.right;
                prevLeft = false;
            }
        }
        if (prevLeft) {
            prev.left = new Node<>(key, value);
        } else {
            prev.right = new Node<>(key, value);
        }
        size++;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set setNode() {
        Set<Node<K, V>> set = new LinkedHashSet<>();
        addToSet(root, set);
        return set;
    }

    private void removeTreeEntry(Node<K, V> current, Node<K, V> prev) {
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else {
                if (current == prev.left) prev.left = null;
                else prev.right = null;
            }
        } else if (current.left == null) {
            current.key = current.right.key;
            current.value = current.right.value;
            current.left = current.right.left;
            current.right = current.right.right;
        } else if (current.right == null) {
            current.key = current.left.key;
            current.value = current.left.value;
            current.right = current.left.right;
            current.left = current.left.left;
        } else {
            Node<K, V> next = current.left;
            while (next.right != null) {
                next = next.right;
            }
            current.key = next.key;
            current.value = next.value;
            removeTreeEntry(next, current);
        }
    }

    @Override
    public String toString() {
        Set<Node<K, V>> set = new LinkedHashSet<>();
        addToSet(root, set);
        return set.toString();
    }

    private void addToSet(Node<K, V> node, Set<Node<K, V>> set) {
        if (node == null) {
            return;
        }
        addToSet(node.left, set);
        set.add(node);
        addToSet(node.right, set);
    }

    private Node<K, V> findEntry(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }
}