@Test
    public void testAddAll()
    {
        ObjectOpenHashSet<Object> set2 = new ObjectOpenHashSet<Object>();
        set2.add(newArray(set2.keys, 1, 2));
        set.add(newArray(set2.keys, 0, 1));

        assertEquals(1, set.addAll(set2));
        assertEquals(0, set.addAll(set2));

        assertEquals(3, set.size());
        assertSortedListEquals(set.toArray(), 0, 1, 2);
    }