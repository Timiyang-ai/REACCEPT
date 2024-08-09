    @Test
    void plus() {
        assertNotNull(Expressions.plus(ToShort.of(s -> (short) 1), 1));
        assertNotNull(Expressions.plus(ToShort.of(s -> (short) 1), ToShort.of(s -> (short) 1)));

        assertNotNull(Expressions.plus(ToFloat.of(f -> 1f), 1f));
        assertNotNull(Expressions.plus(ToFloat.of(f -> 1f), ToFloat.of(f -> 1f)));
    }