public static <T> ToDouble<T> pow(ToShort<T> expression, double power) {
        return object -> Math.pow(expression.applyAsShort(object), power);
    }