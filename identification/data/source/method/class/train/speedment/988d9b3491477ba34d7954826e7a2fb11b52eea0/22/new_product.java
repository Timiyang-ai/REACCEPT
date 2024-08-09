public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, int power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.floatPowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }