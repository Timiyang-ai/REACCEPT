public static <T> ToDoubleNullable<T>
    powOrNull(ToByteNullable<T> expression, ToDouble<T> power) {
        return new ToDoubleNullableImpl<>(
            object -> Math.pow(
                expression.applyAsByte(object),
                power.applyAsDouble(object)
            ), expression::isNull
        );
    }