    @Test
    void minus() {
        assertNotNull(Expressions.minus(ToShort.of(s -> (short) 1), 1));
        assertNotNull(Expressions.minus(ToShort.of(s -> (short) 1), ToShort.of(s -> (short) 1)));

        assertNotNull(Expressions.minus(ToFloat.of(f -> 1f), 1f));
        assertNotNull(Expressions.minus(ToFloat.of(f -> 1f), ToFloat.of(f -> 1f)));
    }