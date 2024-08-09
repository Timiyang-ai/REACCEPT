@Test
    public void testRemoveAllFromLookupContainer()
    {
        set.add(newArray(set.keys, 0, 1, 2, 3, 4));

        ObjectOpenHashSet<Object> list2 = new ObjectOpenHashSet<Object>();
        list2.add(newArray(list2.keys, 1, 3, 5));

        assertEquals(2, set.removeAll(list2));
        assertEquals(3, set.size());
        assertSortedListEquals(set.toArray(), 0, 2, 4);
    }