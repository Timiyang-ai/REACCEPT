@Test
    public void testIterator() {
        assertFalse(emptyVector().iterator().hasNext());
        try {
            emptyVector().iterator().next();
            fail("iterator.next() should throw exception");
        } catch (NoSuchElementException x) {
            /* no-op */
        }
    
        Iterator<VectorEntry> iter = singleton().iterator();
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
    
        Long2DoubleMap.Entry[] entries = Iterators.toArray(
                simpleVector().iterator(), Long2DoubleMap.Entry.class);
        assertEquals(3, entries.length);
        assertEquals(3, entries[0].getLongKey());
        assertEquals(7, entries[1].getLongKey());
        assertEquals(8, entries[2].getLongKey());
        assertThat(entries[0].getDoubleValue(), closeTo(1.5));
        assertThat(entries[1].getDoubleValue(), closeTo(3.5));
        assertThat(entries[2].getDoubleValue(), closeTo(2));
    }