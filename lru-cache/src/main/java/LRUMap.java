public interface LRUMap<K, V> {
    V get(K key);

    V put(K key, V value);

    V remove(K key);
}
