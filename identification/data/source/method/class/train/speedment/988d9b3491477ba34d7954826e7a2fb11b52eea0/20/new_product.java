public static <T> ToDoubleNullable<T>
    powOrNull(ToIntNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.intPowDouble(expression.orThrow(), power),
            expression.isNull()
        );
    }