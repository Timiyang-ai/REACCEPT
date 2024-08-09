public static <T> ToDoubleNullable<T>
    powOrNull(ToShortNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(
                expression.applyAsShort(object),
                power.applyAsDouble(object)
            ), expression::isNull
        );
    }