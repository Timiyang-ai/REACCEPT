public static <T> ToDoubleNullable<T>
    powOrNull(ToIntNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.intPowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }