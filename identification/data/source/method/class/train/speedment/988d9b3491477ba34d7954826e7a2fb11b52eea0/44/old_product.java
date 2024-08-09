public static <T> ToDouble<T> pow(ToDouble<T> expression, double power) {
        return object -> Math.pow(expression.applyAsDouble(object), power);
    }