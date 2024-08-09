public static <T> ToLongNullable<T>
    powOrNull(ToShortNullable<T> expression, int power) {
        switch (power) {
            case 0 : return new ToLongNullableImpl<>(
                object -> 1, object -> false);
            case 1 : return new ToLongNullableImpl<>(
                expression::applyAsShort,
                expression::isNull);
            case 2 : return new ToLongNullableImpl<>(object -> {
                final short v = expression.applyAsShort(object);
                return v * v;
            }, expression::isNull);
            case 3 : return new ToLongNullableImpl<>(object -> {
                final short v = expression.applyAsShort(object);
                return v * v * v;
            }, expression::isNull);
        }

        return new ToLongNullableImpl<>(
            object -> (long) Math.pow(expression.applyAsShort(object), power),
            expression::isNull
        );
    }