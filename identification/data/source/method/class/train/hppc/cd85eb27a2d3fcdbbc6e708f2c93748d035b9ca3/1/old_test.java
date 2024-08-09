@Test
    public void testRemoveAllOccurrences()
    {
        deque.addLast(asArray(0, 1, 2, 1, 0, 3, 0));
        
        assertEquals(0, deque.removeAllOccurrences(k4));
        assertEquals(3, deque.removeAllOccurrences(k0));
        assertListEquals(deque.toArray(), 1, 2, 1, 3);
        assertEquals(1, deque.removeAllOccurrences(k3));
        assertListEquals(deque.toArray(), 1, 2, 1);
        assertEquals(2, deque.removeAllOccurrences(k1));
        assertListEquals(deque.toArray(), 2);
        assertEquals(1, deque.removeAllOccurrences(k2));
        assertEquals(0, deque.size());
    }