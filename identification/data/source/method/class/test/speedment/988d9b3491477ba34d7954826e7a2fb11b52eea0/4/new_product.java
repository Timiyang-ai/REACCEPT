public static <T> ToDoubleNullable<T>
    powOrNull(ToShortNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.shortPowDouble(expression.orThrow(), power),
            expression.isNull()
        );
    }