    @ParameterizedTest
    @ValueSource(bytes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void bytePowInt(byte input) {
        final ToByte<Byte> toByte = b -> b;

        final ToDouble<Byte> toZeroth = PowUtil.bytePowInt(toByte, 0);
        assertEquals(1, toZeroth.applyAsDouble(input));

        final ToDouble<Byte> toFirst = PowUtil.bytePowInt(toByte, 1);
        assertEquals(input, toFirst.applyAsDouble(input));

        final ToDouble<Byte> toSecond = PowUtil.bytePowInt(toByte, 2);
        assertEquals(input * input, toSecond.applyAsDouble(input));

        final ToDouble<Byte> toThird = PowUtil.bytePowInt(toByte, 3);
        assertEquals(input * input * input, toThird.applyAsDouble(input));

        final ToDouble<Byte> reciprocal = PowUtil.bytePowInt(toByte, -1);
        assertEquals(1d / input, reciprocal.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Byte> toNth = PowUtil.bytePowInt(toByte, n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });

        final ToDouble<Byte> toZeroth_ = PowUtil.bytePowInt(toByte, i -> 0);
        assertEquals(1, toZeroth_.applyAsDouble(input));

        final ToDouble<Byte> toFirst_ = PowUtil.bytePowInt(toByte, i -> 1);
        assertEquals(input, toFirst_.applyAsDouble(input));

        final ToDouble<Byte> toSecond_ = PowUtil.bytePowInt(toByte, i -> 2);
        assertEquals(input * input, toSecond_.applyAsDouble(input));

        final ToDouble<Byte> toThird_ = PowUtil.bytePowInt(toByte, i -> 3);
        assertEquals(input * input * input, toThird_.applyAsDouble(input));

        final ToDouble<Byte> reciprocal_ = PowUtil.bytePowInt(toByte, i -> -1);
        assertEquals(1d / input, reciprocal_.applyAsDouble(input));

        IntStream.range(4, 10).forEach(n -> {
            final ToDouble<Byte> toNth = PowUtil.bytePowInt(toByte, i -> n);
            assertEquals(Math.pow(input, n), toNth.applyAsDouble(input));
        });
    }