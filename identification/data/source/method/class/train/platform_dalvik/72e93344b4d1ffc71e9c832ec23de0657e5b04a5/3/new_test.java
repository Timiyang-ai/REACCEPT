    public void test_iterator() {
        BitIntSet set = new BitIntSet(32);

        set.add(0);
        set.add(0);
        set.add(1);
        set.add(1);
        set.add(31);
        set.add(31);

        IntIterator iter = set.iterator();

        assertTrue(iter.hasNext());
        assertEquals(iter.next(), 0);
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), 1);
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), 31);

        assertFalse(iter.hasNext());

        try {
            iter.next();
            fail();
        } catch (NoSuchElementException ex) {
            // exception excepted
        }
    }