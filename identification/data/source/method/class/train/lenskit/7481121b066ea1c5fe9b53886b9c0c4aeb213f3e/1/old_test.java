@Test
    public void testFastIterator() {
        assertFalse(emptyVector().fastIterator().hasNext());
        try {
            emptyVector().fastIterator().next();
            fail("iterator.next() should throw exception");
        } catch (NoSuchElementException x) {
            /* no-op */
        }

        Iterator<VectorEntry> iter = singleton().fastIterator();
        assertTrue(iter.hasNext());
        try {
            iter.remove();
            fail("should throw exception because we cannot remove an item from an iterator on an immutable vector");
        } catch (UnsupportedOperationException x) { /* good*/ }
        VectorEntry e = iter.next();
        assertFalse(iter.hasNext());
        assertThat(e.getKey(), equalTo(5L));
        assertThat(e.getValue(), closeTo(Math.PI));
        try {
            iter.next();
            fail("iter.next() should throw exception");
        } catch (NoSuchElementException x) {
            /* no-op */
        }

        Long[] keys = Iterators.toArray(
                Iterators.transform(simpleVector().fastIterator(),
                                    new Function<VectorEntry, Long>() {
                                        @Override
                                        public Long apply(VectorEntry e) {
                                            return e.getKey();
                                        }
                                    }), Long.class);
        assertThat(keys, equalTo(new Long[]{3l, 7l, 8l}));
    }