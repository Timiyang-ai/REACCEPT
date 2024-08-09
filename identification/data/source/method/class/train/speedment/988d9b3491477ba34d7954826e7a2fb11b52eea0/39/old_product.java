public static <T> ToDoubleNullable<T>
    powOrNull(ToLongNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(
                expression.applyAsLong(object),
                power.applyAsDouble(object)
            ), expression::isNull
        );
    }