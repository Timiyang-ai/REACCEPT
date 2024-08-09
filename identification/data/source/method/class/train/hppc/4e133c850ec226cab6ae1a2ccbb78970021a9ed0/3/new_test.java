@Test
    public void testAddAllLast()
    {
        ObjectArrayList<Object> list2 = new ObjectArrayList<Object>();
        list2.add(newArray(list2.buffer, 0, 1, 2));

        deque.addLast(list2.iterator());
        assertListEquals(deque.toArray(), 0, 1, 2);
        deque.addLast(list2);
        assertListEquals(deque.toArray(), 0, 1, 2, 0, 1, 2);

        deque.clear();
        ObjectArrayDeque<Object> deque2 = new ObjectArrayDeque<Object>();
        deque2.addLast(newArray(deque2.buffer, 0, 1, 2));
        deque.addLast(deque2);
        assertListEquals(deque.toArray(), 0, 1, 2);
    }