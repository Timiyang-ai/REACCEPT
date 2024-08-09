    @ParameterizedTest
    @ValueSource(shorts = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void shortPowInt(short input) {
        final ToShort<Short> toShort = s -> s;

        final ToDouble<Short> toZeroth = PowUtil.shortPowInt(toShort, 0);
        assertEquals(1, toZeroth.applyAsDouble(input));

        final ToDouble<Short> toFirst = PowUtil.shortPowInt(toShort, 1);
        assertEquals(input, toFirst.applyAsDouble(input));

        final ToDouble<Short> toSecond = PowUtil.shortPowInt(toShort, 2);
        assertEquals(input * input, toSecond.applyAsDouble(input));

        final ToDouble<Short> toThird = PowUtil.shortPowInt(toShort, 3);
        assertEquals(input * input * input, toThird.applyAsDouble(input));

        final ToDouble<Short> reciprocal = PowUtil.shortPowInt(toShort, -1);
        assertEquals(1d / input, reciprocal.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Short> toNth = PowUtil.shortPowInt(toShort, n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });

        final ToDouble<Short> toZeroth_ = PowUtil.shortPowInt(toShort, i -> 0);
        assertEquals(1, toZeroth_.applyAsDouble(input));

        final ToDouble<Short> toFirst_ = PowUtil.shortPowInt(toShort, i -> 1);
        assertEquals(input, toFirst_.applyAsDouble(input));

        final ToDouble<Short> toSecond_ = PowUtil.shortPowInt(toShort, i -> 2);
        assertEquals(input * input, toSecond_.applyAsDouble(input));

        final ToDouble<Short> toThird_ = PowUtil.shortPowInt(toShort, i -> 3);
        assertEquals(input * input * input, toThird_.applyAsDouble(input));

        final ToDouble<Short> reciprocal_ = PowUtil.shortPowInt(toShort, i -> -1);
        assertEquals(1d / input, reciprocal_.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Short> toNth = PowUtil.shortPowInt(toShort, i -> n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });
    }