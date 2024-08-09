@Test
    public void testRemoveAll()
    {
        deque.addLast(asArray(0, 1, 2, 1, 0, 3, 0));
        
        assertEquals(0, deque.removeAll(k4));
        assertEquals(3, deque.removeAll(k0));
        assertListEquals(deque.toArray(), 1, 2, 1, 3);
        assertEquals(1, deque.removeAll(k3));
        assertListEquals(deque.toArray(), 1, 2, 1);
        assertEquals(2, deque.removeAll(k1));
        assertListEquals(deque.toArray(), 2);
        assertEquals(1, deque.removeAll(k2));
        assertEquals(0, deque.size());
    }