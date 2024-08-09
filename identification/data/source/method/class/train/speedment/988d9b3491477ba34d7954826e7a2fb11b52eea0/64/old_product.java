public static <T> ToDoubleNullable<T>
    powOrNull(ToLongNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsLong(object), power),
            expression::isNull
        );
    }