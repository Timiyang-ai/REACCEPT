public static <T> ToDoubleNullable<T>
    powOrNull(ToDoubleNullable<T> expression, int power) {
        switch (power) {
            case 0 : return new ToDoubleNullableImpl<>(
                object -> 1d,
                object -> false);
            case 1 : return new ToDoubleNullableImpl<>(
                expression::applyAsDouble,
                expression::isNull);
            case 2 : return new ToDoubleNullableImpl<>(object -> {
                final double v = expression.applyAsDouble(object);
                return v * v;
            }, expression::isNull);
            case 3 : return new ToDoubleNullableImpl<>(object -> {
                final double v = expression.applyAsDouble(object);
                return v * v * v;
            }, expression::isNull);
        }

        return new ToDoubleNullableImpl<>(
            object -> Math.pow(expression.applyAsDouble(object), power),
            expression::isNull
        );
    }