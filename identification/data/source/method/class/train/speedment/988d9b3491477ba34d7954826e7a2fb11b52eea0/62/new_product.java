public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.floatPowDouble(expression.orThrow(), power),
            expression.isNull()
        );
    }