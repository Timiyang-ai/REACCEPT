public static <T> ToLongNullable<T>
    powOrNull(ToByteNullable<T> expression, int power) {
        switch (power) {
            case 0 : return new ToLongNullableImpl<>(
                object -> 1, object -> false);
            case 1 : return new ToLongNullableImpl<>(
                expression::applyAsByte,
                expression::isNull);
            case 2 : return new ToLongNullableImpl<>(object -> {
                final byte v = expression.applyAsByte(object);
                return v * v;
            }, expression::isNull);
            case 3 : return new ToLongNullableImpl<>(object -> {
                final byte v = expression.applyAsByte(object);
                return v * v * v;
            }, expression::isNull);
        }

        return new ToLongNullableImpl<>(
            object -> (long) Math.pow(expression.applyAsByte(object), power),
            expression::isNull
        );
    }