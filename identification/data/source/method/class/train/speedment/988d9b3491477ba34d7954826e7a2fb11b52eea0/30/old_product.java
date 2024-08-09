public static <T> ToDouble<T> pow(ToInt<T> expression, double power) {
        return object -> Math.pow(expression.applyAsInt(object), power);
    }