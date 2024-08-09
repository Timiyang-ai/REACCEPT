public static <T> ToDoubleNullable<T>
    powOrNull(ToLongNullable<T> expression, int power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.longPowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }