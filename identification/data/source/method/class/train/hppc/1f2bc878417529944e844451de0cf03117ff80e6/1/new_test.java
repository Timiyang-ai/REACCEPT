@Test
    public void testAddAll()
    {
        KTypeOpenHashSet<KType> set2 = new KTypeOpenHashSet<KType>();
        set2.addAll(asArray(1, 2));
        set.addAll(asArray(0, 1));

        assertEquals(1, set.addAll(set2));
        assertEquals(0, set.addAll(set2));

        assertEquals(3, set.size());
        assertSortedListEquals(set.toArray(), 0, 1, 2);
    }