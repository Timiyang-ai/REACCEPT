public static <T> ToDoubleNullable<T>
    powOrNull(ToDoubleNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.doublePowDouble(expression.orThrow(), power),
            expression.isNull()
        );
    }