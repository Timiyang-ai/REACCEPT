public static <T> ToDouble<T> pow(ToFloat<T> expression, int power) {
        switch (power) {
            case 0 : return object -> 1d;
            case 1 : return expression.asDouble();
            case 2 : return object -> {
                final double v = expression.applyAsFloat(object);
                return v * v;
            };
            case 3 : return object -> {
                final double v = expression.applyAsFloat(object);
                return v * v * v;
            };
        }

        return object -> Math.pow(expression.applyAsFloat(object), power);
    }