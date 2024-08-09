public static <T> ToDoubleNullable<T>
    powOrNull(ToDoubleNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.doublePowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }