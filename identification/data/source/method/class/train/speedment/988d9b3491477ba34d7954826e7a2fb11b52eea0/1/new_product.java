public static <T> ToDoubleNullable<T>
    powOrNull(ToByteNullable<T> expression, int power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.bytePowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }