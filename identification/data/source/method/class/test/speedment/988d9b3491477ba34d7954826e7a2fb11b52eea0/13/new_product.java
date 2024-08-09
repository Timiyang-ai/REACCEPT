public static <T> ToDoubleNullable<T>
    powOrNull(ToLongNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.longPowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }