public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(
                expression.applyAsFloat(object),
                power.applyAsDouble(object)
            ), expression::isNull
        );
    }