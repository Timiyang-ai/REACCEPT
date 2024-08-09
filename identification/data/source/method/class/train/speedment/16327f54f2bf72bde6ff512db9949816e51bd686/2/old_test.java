    @Test
    void flatMap() {
        final long expected = refStream().flatMap(e -> e.getKey().chars().boxed()).count();
        final long actual = instance.flatMap(e -> e.getKey().chars().boxed()).count();
        assertEquals(expected, actual);
    }