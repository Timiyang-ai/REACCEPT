    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void intPowInt(int input) {
        final ToInt<Integer> toInt = i -> i;

        final ToDouble<Integer> toZeroth = PowUtil.intPowInt(toInt, 0);
        assertEquals(1, toZeroth.applyAsDouble(input));

        final ToDouble<Integer> toFirst = PowUtil.intPowInt(toInt, 1);
        assertEquals(input, toFirst.applyAsDouble(input));

        final ToDouble<Integer> toSecond = PowUtil.intPowInt(toInt, 2);
        assertEquals(input * input, toSecond.applyAsDouble(input));

        final ToDouble<Integer> toThird = PowUtil.intPowInt(toInt, 3);
        assertEquals(input * input * input, toThird.applyAsDouble(input));

        final ToDouble<Integer> reciprocal = PowUtil.intPowInt(toInt, -1);
        assertEquals(1d / input, reciprocal.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Integer> toNth = PowUtil.intPowInt(toInt, n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });

        final ToDouble<Integer> toZeroth_ = PowUtil.intPowInt(toInt, i -> 0);
        assertEquals(1, toZeroth_.applyAsDouble(input));

        final ToDouble<Integer> toFirst_ = PowUtil.intPowInt(toInt, i -> 1);
        assertEquals(input, toFirst_.applyAsDouble(input));

        final ToDouble<Integer> toSecond_ = PowUtil.intPowInt(toInt, i -> 2);
        assertEquals(input * input, toSecond_.applyAsDouble(input));

        final ToDouble<Integer> toThird_ = PowUtil.intPowInt(toInt, i -> 3);
        assertEquals(input * input * input, toThird_.applyAsDouble(input));

        final ToDouble<Integer> reciprocal_ = PowUtil.intPowInt(toInt, i -> -1);
        assertEquals(1d / input, reciprocal_.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Integer> toNth = PowUtil.intPowInt(toInt, i -> n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });
    }