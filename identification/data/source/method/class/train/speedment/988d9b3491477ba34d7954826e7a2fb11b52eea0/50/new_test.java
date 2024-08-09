    @Test
    void pow() {
        assertNotNull(Expressions.pow(ToByte.of(b -> (byte) 1), ToDouble.constant(1)));
        assertNotNull(Expressions.pow(ToShort.of(s -> (short) 1), ToDouble.constant(1)));
        assertNotNull(Expressions.pow(ToInt.of(i -> 1), ToDouble.constant(1)));
        assertNotNull(Expressions.pow(ToLong.of(l -> 1), ToDouble.constant(1)));
        assertNotNull(Expressions.pow(ToFloat.of(f -> 1), ToDouble.constant(1)));
        assertNotNull(Expressions.pow(ToDouble.of(d -> 1), ToDouble.constant(1)));
    }