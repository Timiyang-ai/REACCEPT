    public void test_remove() {
        BitIntSet set = new BitIntSet(32);

        set.add(0);
        set.add(1);
        set.add(31);

        assertTrue(set.has(0));
        assertTrue(set.has(1));
        assertTrue(set.has(31));

        assertFalse(set.has(2));
        assertFalse(set.has(7));
        assertFalse(set.has(30));

        set.remove(0);

        assertFalse(set.has(0));

        assertTrue(set.has(1));
        assertTrue(set.has(31));
    }