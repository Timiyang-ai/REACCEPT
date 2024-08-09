public static <T> ToDoubleNullable<T>
    powOrNull(ToShortNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsShort(object), power),
            expression::isNull
        );
    }