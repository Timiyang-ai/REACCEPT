public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.floatPowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }