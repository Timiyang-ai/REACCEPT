    @Test
    void getOrCompute() {
        final LongCache<String> lc = new LongCache<>(10);
        final long actual = lc.getOrCompute("one", () -> 1L);
        assertEquals(1L, actual);
    }