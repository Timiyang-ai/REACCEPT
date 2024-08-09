public static <T> ToDoubleNullable<T>
    powOrNull(ToDoubleNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.pow(expression.orThrow(), power),
            expression.isNull()
        );
    }