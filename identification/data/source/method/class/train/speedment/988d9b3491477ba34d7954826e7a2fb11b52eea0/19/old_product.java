public static <T> ToLongNullable<T>
    powOrNull(ToIntNullable<T> expression, int power) {
        switch (power) {
            case 0 : return new ToLongNullableImpl<>(
                object -> 1, object -> false);
            case 1 : return new ToLongNullableImpl<>(
                expression::applyAsInt,
                expression::isNull);
            case 2 : return new ToLongNullableImpl<>(object -> {
                final int v = expression.applyAsInt(object);
                return v * v;
            }, expression::isNull);
            case 3 : return new ToLongNullableImpl<>(object -> {
                final int v = expression.applyAsInt(object);
                return v * v * v;
            }, expression::isNull);
        }

        return new ToLongNullableImpl<>(
            object -> (long) Math.pow(expression.applyAsInt(object), power),
            expression::isNull
        );
    }