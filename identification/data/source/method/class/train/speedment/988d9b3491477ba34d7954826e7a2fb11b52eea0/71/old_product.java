public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, double power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsFloat(object), power),
            expression::isNull
        );
    }