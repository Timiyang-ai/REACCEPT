    @Test
    public void iterator() {
        Iterator<Integer> iterator = FluentIterable.of(iterableA).iterator();
        assertTrue(iterator.hasNext());

        iterator = FluentIterable.<Integer>empty().iterator();
        assertFalse(iterator.hasNext());
    }