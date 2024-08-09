public static <T> ToDoubleNullable<T>
    powOrNull(ToFloatNullable<T> expression, ToInt<T> power) {
        return new ToDoubleNullableImpl<>(object -> {
            final double v = expression.applyAsFloat(object);
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