public static <T> ToDoubleNullable<T>
    powOrNull(ToIntNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsInt(object), power),
            expression::isNull
        );
    }