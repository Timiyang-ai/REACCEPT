    @Test
    void negateOrNull() {
        assertNotNull(Expressions.negateOrNull(ToBooleanNullable.of(b -> true)));
    }