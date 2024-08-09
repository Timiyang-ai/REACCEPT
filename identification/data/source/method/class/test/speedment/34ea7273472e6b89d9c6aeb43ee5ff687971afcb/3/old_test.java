    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void longPowInt(long input) {
        final ToLong<Long> toLong = l -> l;

        final ToDouble<Long> toZeroth = PowUtil.longPowInt(toLong, 0);
        assertEquals(1, toZeroth.applyAsDouble(input));

        final ToDouble<Long> toFirst = PowUtil.longPowInt(toLong, 1);
        assertEquals(input, toFirst.applyAsDouble(input));

        final ToDouble<Long> toSecond = PowUtil.longPowInt(toLong, 2);
        assertEquals(input * input, toSecond.applyAsDouble(input));

        final ToDouble<Long> toThird = PowUtil.longPowInt(toLong, 3);
        assertEquals(input * input * input, toThird.applyAsDouble(input));

        final ToDouble<Long> reciprocal = PowUtil.longPowInt(toLong, -1);
        assertEquals(1d / input, reciprocal.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Long> toNth = PowUtil.longPowInt(toLong, n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });

        final ToDouble<Long> toZeroth_ = PowUtil.longPowInt(toLong, i -> 0);
        assertEquals(1, toZeroth_.applyAsDouble(input));

        final ToDouble<Long> toFirst_ = PowUtil.longPowInt(toLong, i -> 1);
        assertEquals(input, toFirst_.applyAsDouble(input));

        final ToDouble<Long> toSecond_ = PowUtil.longPowInt(toLong, i -> 2);
        assertEquals(input * input, toSecond_.applyAsDouble(input));

        final ToDouble<Long> toThird_ = PowUtil.longPowInt(toLong, i -> 3);
        assertEquals(input * input * input, toThird_.applyAsDouble(input));

        final ToDouble<Long> reciprocal_ = PowUtil.longPowInt(toLong, i -> -1);
        assertEquals(1d / input, reciprocal_.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Long> toNth = PowUtil.longPowInt(toLong, i -> n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });
    }