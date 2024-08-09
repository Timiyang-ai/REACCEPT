public static <T> ToDoubleNullable<T>
    powOrNull(ToLongNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.longPowDouble(expression.orThrow(), power),
            expression.isNull()
        );
    }