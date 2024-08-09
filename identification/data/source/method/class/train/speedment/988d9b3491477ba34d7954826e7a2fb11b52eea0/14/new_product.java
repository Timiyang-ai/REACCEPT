public static <T> ToDoubleNullable<T>
    powOrNull(ToShortNullable<T> expression, int power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.shortPowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }