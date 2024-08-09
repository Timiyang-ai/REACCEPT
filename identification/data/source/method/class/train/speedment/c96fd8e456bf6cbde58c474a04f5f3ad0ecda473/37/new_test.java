    @Test
    void multiply() {
        assertNotNull(Expressions.multiply(ToShort.of(s -> (short) 1), 1));
        assertNotNull(Expressions.multiply(ToShort.of(s -> (short) 1), ToShort.of(s -> (short) 1)));

        assertNotNull(Expressions.multiply(ToFloat.of(f -> 1f), 1f));
        assertNotNull(Expressions.multiply(ToFloat.of(f -> 1f), ToFloat.of(f -> 1f)));
    }