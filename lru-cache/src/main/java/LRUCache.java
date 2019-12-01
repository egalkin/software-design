import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class LRUCache<K, V> implements LRUMap<K, V> {
    @NotNull
    private HashMap<K, Node<K, V>> entries;

    @Nullable
    private LRUCache.Node<K, V> head;

    @Nullable
    private LRUCache.Node<K, V> tail;

    private int capacity;
    private int size;

    static class Node<K, V> {
        private Node<K, V> prev, next;
        private K key;
        private V value;

        Node(K key, V value, Node<K, V> next, Node<K, V> prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        Node(K key, V value) {
            this(key, value, null, null);
        }

        Node() {
            this(null, null);
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

    }

    LRUCache(int capacity) {
        assert capacity >= 1;
        this.head = this.tail = new Node<>();
        this.entries = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
    }

    public K getMostUsedValue() {
        return this.tail.key;
    }

    public int size() {
        return this.size;
    }

    public Set<K> keySet() {
        return this.entries.keySet();
    }

    public List<K> getLRUOrder() {
        List<K> order = new ArrayList<>();
        Node<K, V> i = this.head;
        while (i.next != null) {
            i = i.next;
            order.add(i.key);
        }
        return order;
    }

    public void updateCapacity(int newCapacity) {
        assert newCapacity >= 1;
        this.capacity = newCapacity;
        if (this.size >= newCapacity) {
            for (int i = 0; i < size - newCapacity + 1; ++i) {
                remove(head.next.key);
            }
            assert this.size == newCapacity;
        }
        assert this.capacity == newCapacity;
    }

    public int capacity() {
        return this.capacity;
    }

    @Override
    public V put(K key, V value) {
        if (entries.containsKey(key)) {
            Node<K, V> prevValue = entries.get(key);
            V replacedValue = prevValue.value;
            prevValue.value = value;
            propagateToLast(prevValue);
            assert this.tail == prevValue;
            return replacedValue;
        } else {
            if (size == capacity) {
                remove(head.next.key);
            }
            int prevSize = size;
            Node<K, V> newValue = new Node<>(key, value);
            addLast(newValue);
            entries.put(key, newValue);
            size++;
            assert this.tail == newValue;
            assert size == prevSize + 1;
            return null;
        }
    }

    @Override
    public V remove(K key) {
        if (entries.containsKey(key)) {
            int prevSize = size;
            Node<K, V> erasedNode = entries.get(key);
            eraseNode(erasedNode);
            entries.remove(key);
            size--;
            assert size == prevSize - 1;
            return erasedNode.value;
        }
        return null;
    }

    @Override
    public V get(K key) {
        if (entries.containsKey(key)) {
            Node<K, V> accessedValue = entries.get(key);
            propagateToLast(accessedValue);
            assert this.tail == accessedValue;
            return accessedValue.value;
        }
        return null;
    }


    private void addLast(@NotNull Node<K, V> newTail) {
        newTail.prev = this.tail;
        newTail.next = null;
        this.tail.next = newTail;
        this.tail = newTail;
        assert this.tail == newTail;
    }

    private void eraseNode(@NotNull Node<K, V> erasedNode) {
        Node<K, V> prev, next;
        prev = erasedNode.prev;
        next = erasedNode.next;
        if (prev != null) {
            assert prev.next == erasedNode;
            prev.next = next;
            assert prev.next == next;
        }
        if (next != null) {
            assert next.prev == erasedNode;
            next.prev = prev;
            assert next.prev == prev;
        }
        if (next == null) {
            assert erasedNode.next == null;
            this.tail = prev;
        }
    }

    private void propagateToLast(@NotNull Node<K, V> propagatedNode) {
        eraseNode(propagatedNode);
        addLast(propagatedNode);
        assert this.tail == propagatedNode;
    }


}