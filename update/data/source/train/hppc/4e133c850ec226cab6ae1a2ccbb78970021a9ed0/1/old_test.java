@Test
    public void testRemoveAllIn()
    {
        deque.addLast(newArray(deque.buffer, 0, 1, 2, 1, 0));
        
        ObjectArrayList<Object> list2 = new ObjectArrayList<Object>();
        list2.add(newArray(list2.buffer, 0, 2));

        assertEquals(3, deque.removeAllIn(list2));
        assertEquals(0, deque.removeAllIn(list2.iterator()));

        assertListEquals(deque.toArray(), 1, 1);
    }