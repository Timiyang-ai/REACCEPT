@Test
    public void testAddVarArgs()
    {
        set.add(newArray(set.keys, 0, 1, 2, 1, 0));
        assertEquals(3, set.size());
        assertSortedListEquals(set.toArray(), 0, 1, 2);
    }