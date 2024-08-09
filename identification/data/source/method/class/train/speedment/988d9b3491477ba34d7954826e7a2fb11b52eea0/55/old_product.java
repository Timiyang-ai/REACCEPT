public static <T> ToDouble<T> pow(ToFloat<T> expression, double power) {
        return object -> Math.pow(expression.applyAsFloat(object), power);
    }