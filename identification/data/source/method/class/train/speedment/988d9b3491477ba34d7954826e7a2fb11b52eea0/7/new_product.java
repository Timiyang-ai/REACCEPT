public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.floatPowDouble(expression.orThrow(), power),
            expression.isNull()
        );
    }