public static <T> ToDoubleNullable<T>
    powOrNull(ToByteNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.pow(expression.orThrow(), power),
            expression.isNull()
        );
    }