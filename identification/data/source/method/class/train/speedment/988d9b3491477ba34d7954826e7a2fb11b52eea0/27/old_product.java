public static <T> ToDoubleNullable<T>
    powOrNull(ToByteNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsByte(object), power),
            expression::isNull
        );
    }