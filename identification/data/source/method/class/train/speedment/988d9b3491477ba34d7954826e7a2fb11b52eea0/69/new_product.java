public static <T> ToDouble<T> pow(ToFloat<T> expression, ToInt<T> power) {
        return PowUtil.pow(expression, power);
    }