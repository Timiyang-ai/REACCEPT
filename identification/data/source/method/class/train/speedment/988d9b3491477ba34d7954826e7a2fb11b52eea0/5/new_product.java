public static <T> ToDoubleNullable<T>
    powOrNull(ToShortNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.shortPowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }