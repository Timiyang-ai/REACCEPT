public static <T> ToDoubleNullable<T>
    powOrNull(ToIntNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(
                expression.applyAsInt(object),
                power.applyAsDouble(object)
            ), expression::isNull
        );
    }