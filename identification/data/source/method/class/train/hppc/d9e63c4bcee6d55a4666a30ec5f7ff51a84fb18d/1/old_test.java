@Test
    public void testRemoveAllIn()
    {
        set.add(newArray(set.keys, 0, 1, 2));
        
        ObjectArrayList<Object> list2 = new ObjectArrayList<Object>();
        list2.add(newArray(list2.buffer, 1, 3));

        set.removeAllIn(list2);
        assertEquals(2, set.size());
        assertSortedListEquals(set.toArray(), 0, 2);
    }