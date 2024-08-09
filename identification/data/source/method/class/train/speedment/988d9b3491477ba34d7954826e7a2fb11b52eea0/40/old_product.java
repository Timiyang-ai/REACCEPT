public static <T> ToLongNullable<T>
    powOrNull(ToShortNullable<T> expression, ToInt<T> power) {
        return new ToLongNullableImpl<>(object -> {
            final short v = expression.applyAsShort(object);
            final int p = power.applyAsInt(object);
            switch (p) {
                case 0 : return 1;
                case 1 : return v;
                case 2 : return v * v;
                case 3 : return v * v * v;
            }
            return (long) Math.pow(v, p);
        }, expression::isNull);
    }