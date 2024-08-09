public static <T> ToDoubleNullable<T>
    powOrNull(ToIntNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.pow(expression.orThrow(), power),
            expression.isNull()
        );
    }