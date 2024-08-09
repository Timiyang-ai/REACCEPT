public static <T> ToDoubleNullable<T>
    powOrNull(ToDoubleNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsDouble(object), power),
            expression::isNull
        );
    }