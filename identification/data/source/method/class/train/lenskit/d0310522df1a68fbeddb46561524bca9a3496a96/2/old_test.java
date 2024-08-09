@Test
    public void testFastIterator() {
        assertFalse(emptyVector().fastIterator().hasNext());
        try {
            emptyVector().fastIterator().next();
            fail("iterator.next() should throw exception");
        } catch (NoSuchElementException x) {
            /* no-op */
        }
    
        Iterator<Long2DoubleMap.Entry> iter = singleton().fastIterator();
        assertTrue(iter.hasNext());
        Long2DoubleMap.Entry e = iter.next();
        assertFalse(iter.hasNext());
        assertEquals(5, e.getLongKey());
        assertEquals(Long.valueOf(5), e.getKey());
        assertThat(e.getDoubleValue(), closeTo(Math.PI));
        assertThat(e.getValue(), closeTo(Double.valueOf(Math.PI)));
        try {
            iter.next();
            fail("iter.next() should throw exception");
        } catch (NoSuchElementException x) {
            /* no-op */
        }
    
        Long[] keys = Iterators.toArray(
                Iterators.transform(simpleVector().fastIterator(),
                        new Function<Long2DoubleMap.Entry,Long>() {
                    @Override
                    public Long apply(Long2DoubleMap.Entry e) {
                        return e.getKey();
                    }
                }), Long.class);
        assertThat(keys, equalTo(new Long[]{3l, 7l, 8l}));
    }