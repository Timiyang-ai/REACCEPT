@Test
    public void testRemoveAllOccurrences()
    {
        deque.addLast(newArray(deque.buffer, 0, 1, 2, 1, 0, 3, 0));
        
        assertEquals(0, deque.removeAllOccurrences(/* intrinsic:ktypecast */ 4));
        assertEquals(3, deque.removeAllOccurrences(/* intrinsic:ktypecast */ 0));
        assertListEquals(deque.toArray(), 1, 2, 1, 3);
        assertEquals(1, deque.removeAllOccurrences(/* intrinsic:ktypecast */ 3));
        assertListEquals(deque.toArray(), 1, 2, 1);
        assertEquals(2, deque.removeAllOccurrences(/* intrinsic:ktypecast */ 1));
        assertListEquals(deque.toArray(), 2);
        assertEquals(1, deque.removeAllOccurrences(/* intrinsic:ktypecast */ 2));
        assertEquals(0, deque.size());
    }