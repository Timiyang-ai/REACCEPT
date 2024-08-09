public static <T> ToDoubleNullable<T>
    powOrNull(ToDoubleNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(object -> {
            final double v = expression.applyAsDouble(object);
            final int p = power.applyAsInt(object);
            switch (p) {
                case 0 : return 1d;
                case 1 : return v;
                case 2 : return v * v;
                case 3 : return v * v * v;
            }
            return Math.pow(v, p);
        }, expression::isNull);
    }