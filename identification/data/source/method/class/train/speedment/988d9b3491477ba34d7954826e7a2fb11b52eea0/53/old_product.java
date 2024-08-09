public static <T> ToDoubleNullable<T>
    powOrNull(ToDoubleNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(
                expression.applyAsDouble(object),
                power.applyAsDouble(object)
            ), expression::isNull
        );
    }