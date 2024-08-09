public static <T> ToDouble<T>
    pow(ToDouble<T> expression, ToInt<T> power) {
        return object -> {
            final double v = expression.applyAsDouble(object);
            final int p = power.applyAsInt(object);
            switch (p) {
                case 0 : return 1;
                case 1 : return v;
                case 2 : return v * v;
                case 3 : return v * v * v;
            }
            return Math.pow(v, p);
        };
    }