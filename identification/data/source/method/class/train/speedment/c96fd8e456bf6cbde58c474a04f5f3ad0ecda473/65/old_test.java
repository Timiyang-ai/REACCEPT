    @Test
    void negate() {
        assertNotNull(Expressions.negate(ToBoolean.of(b -> true)));
    }