public static <T> ToDouble<T> pow(ToFloat<T> expression, ToDouble<T> power) {
        return PowUtil.pow(expression, power);
    }