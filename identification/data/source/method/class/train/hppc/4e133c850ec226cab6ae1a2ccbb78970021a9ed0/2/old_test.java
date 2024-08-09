@Test
    public void testAddAllFirst()
    {
        ObjectArrayList<Object> list2 = new ObjectArrayList<Object>();
        list2.add(newArray(list2.buffer, 0, 1, 2));

        deque.addAllFirst(list2.iterator());
        assertListEquals(deque.toArray(), 2, 1, 0);
        deque.addAllFirst(list2);
        assertListEquals(deque.toArray(), 2, 1, 0, 2, 1, 0);

        deque.clear();
        ObjectArrayDeque<Object> deque2 = new ObjectArrayDeque<Object>();
        deque2.addLast(newArray(deque2.buffer, 0, 1, 2));
        deque.addAllFirst(deque2);
        assertListEquals(deque.toArray(), 2, 1, 0);
    }