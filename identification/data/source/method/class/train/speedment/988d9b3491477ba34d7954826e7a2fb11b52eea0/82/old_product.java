public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, int power) {
        switch (power) {
            case 0 : return new ToDoubleNullableImpl<>(
                object -> 1d, object -> false);
            case 1 : return new ToDoubleNullableImpl<>(
                expression::applyAsFloat,
                expression::isNull);
            case 2 : return new ToDoubleNullableImpl<>(object -> {
                final double v = expression.applyAsFloat(object);
                return v * v;
            }, expression::isNull);
            case 3 : return new ToDoubleNullableImpl<>(object -> {
                final double v = expression.applyAsFloat(object);
                return v * v * v;
            }, expression::isNull);
        }

        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsFloat(object), power),
            expression::isNull
        );
    }