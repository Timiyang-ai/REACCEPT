    @Test
    void powOrNull() {
        assertNotNull(Expressions.powOrNull(ToByteNullable.of(b -> (byte) 1), 1));
        assertNotNull(Expressions.powOrNull(ToByteNullable.of(b -> (byte) 1), ToInt.of(i -> 1)));
        assertNotNull(Expressions.powOrNull(ToByteNullable.of(b -> (byte) 1), 1d));
        assertNotNull(Expressions.powOrNull(ToByteNullable.of(b -> (byte) 1), ToDouble.of(d -> 1)));

        assertNotNull(Expressions.powOrNull(ToShortNullable.of(s -> (short) 1), 1));
        assertNotNull(Expressions.powOrNull(ToShortNullable.of(s -> (short) 1), ToInt.of(i -> 1)));
        assertNotNull(Expressions.powOrNull(ToShortNullable.of(s -> (short) 1), 1d));
        assertNotNull(Expressions.powOrNull(ToShortNullable.of(s -> (short) 1), ToDouble.of(d -> 1)));

        assertNotNull(Expressions.powOrNull(ToIntNullable.of(i -> 1), 1));
        assertNotNull(Expressions.powOrNull(ToIntNullable.of(i -> 1), ToInt.of(i -> 1)));
        assertNotNull(Expressions.powOrNull(ToIntNullable.of(i -> 1), 1d));
        assertNotNull(Expressions.powOrNull(ToIntNullable.of(i -> 1), ToDouble.of(d -> 1)));

        assertNotNull(Expressions.powOrNull(ToLongNullable.of(l -> 1L), 1));
        assertNotNull(Expressions.powOrNull(ToLongNullable.of(l -> 1L), ToInt.of(i -> 1)));
        assertNotNull(Expressions.powOrNull(ToLongNullable.of(l -> 1L), 1d));
        assertNotNull(Expressions.powOrNull(ToLongNullable.of(l -> 1L), ToDouble.of(d -> 1)));

        assertNotNull(Expressions.powOrNull(ToFloatNullable.of(f -> 1f), 1));
        assertNotNull(Expressions.powOrNull(ToFloatNullable.of(f -> 1f), ToInt.of(i -> 1)));
        assertNotNull(Expressions.powOrNull(ToFloatNullable.of(f -> 1f), 1d));
        assertNotNull(Expressions.powOrNull(ToFloatNullable.of(f -> 1f), ToDouble.of(d -> 1)));

        assertNotNull(Expressions.powOrNull(ToDoubleNullable.of(d -> 1d), 1));
        assertNotNull(Expressions.powOrNull(ToDoubleNullable.of(d -> 1d), ToInt.of(i -> 1)));
        assertNotNull(Expressions.powOrNull(ToDoubleNullable.of(d -> 1d), 1d));
        assertNotNull(Expressions.powOrNull(ToDoubleNullable.of(d -> 1d), ToDouble.of(d -> 1)));
    }