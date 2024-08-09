public static <T> ToDouble<T> pow(ToDouble<T> expression, int power) {
        switch (power) {
            case 0 : return object -> 1d;
            case 1 : return expression.asDouble();
            case 2 : return object -> {
                final double v = expression.applyAsDouble(object);
                return v * v;
            };
            case 3 : return object -> {
                final double v = expression.applyAsDouble(object);
                return v * v * v;
            };
        }

        return object -> Math.pow(expression.applyAsDouble(object), power);
    }