public static <T> ToDoubleNullable<T>
    powOrNull(ToByteNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(
            PowUtil.bytePowInt(expression.orThrow(), power),
            expression.isNull()
        );
    }