import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

public class LRUCacheTest {
    @Test
    public void lruCrowdingOutTest() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        for (int i = 0; i < 10; ++i) {
            cache.put(i, i + 10);
        }
        List<Integer> lruOrder = cache.getLRUOrder();
        for (int i = 0; i < lruOrder.size(); ++i) {
            assertEquals(i + lruOrder.size(), lruOrder.get(i));
        }
    }

    @Test
    public void lruOrderTest() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        for (int i = 0; i < 5; ++i) {
            cache.put(i, i + 10);
        }
        cache.get(3);
        assertEquals(3, cache.getMostUsedValue());
    }

    @Test
    public void greaterCapacityUpdateTest() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        for (int i = 0; i < 5; ++i) {
            cache.put(i, i + 10);
        }
        cache.updateCapacity(10);
        assertEquals(10, cache.capacity());
    }

    @Test
    public void lessCapacityUpdateTest() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        for (int i = 0; i < 5; ++i) {
            cache.put(i, i + 10);
        }
        cache.updateCapacity(3);
        List<Integer> lruOrder = cache.getLRUOrder();
        for (int i = 0; i < lruOrder.size(); ++i) {
            assertEquals(i + 2, lruOrder.get(i));
        }
    }

    //    ["LRUCache","get","put","get","put","put","get","get"]
//    [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
    @Test
    public void leetCodeTest1() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        cache.get(2);
        cache.put(2, 6);
        cache.get(1);
        cache.put(1, 5);
        cache.put(1, 2);
        cache.get(1);
        assertEquals(6, cache.get(2));

    }

//   ["LRUCache","put","put","put","put","get","get"]
//   [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]

    @Test
    public void leetCodeTest2() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        assertEquals(null, cache.get(1));
        assertEquals(3, cache.get(2));

    }

//    ["LRUCache","put","put","get","get","put","get","get","get"]
//            [[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]

    @Test
    public void leetCodeTest3() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        cache.put(2, 1);
        cache.put(3, 2);
        cache.get(3);
        cache.get(2);
        cache.put(4, 3);
        cache.get(2);
        cache.get(3);
        cache.get(4);
    }

}
