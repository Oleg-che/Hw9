package com.cherednik.Tree;

import java.util.Set;

public interface MyMap<K, V> {
    interface Node<K, V> {
        K getKey();

        V getValue();

        void setValue(V value);
    }

    void clear();

    boolean containsKey(K key);

    V get(K key);

    boolean isEmpty();

    V put(K key, V value);

    int size();

    Set<Node> setNode();
}
