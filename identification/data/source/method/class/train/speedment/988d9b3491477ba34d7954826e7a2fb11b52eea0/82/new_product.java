public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, int power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.pow(expression.orThrow(), power),
            expression.isNull()
        );
    }