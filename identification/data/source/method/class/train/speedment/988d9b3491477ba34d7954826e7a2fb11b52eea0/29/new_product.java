public static <T> ToDoubleNullable<T>
    powOrNull(ToByteNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.bytePowDouble(expression.orThrow(), power),
            expression.isNull()
        );
    }