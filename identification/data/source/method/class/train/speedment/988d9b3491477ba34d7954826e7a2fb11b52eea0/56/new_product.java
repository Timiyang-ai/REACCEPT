public static <T> ToDouble<T> pow(ToDouble<T> expression, ToDouble<T> power) {
        return PowUtil.doublePowDouble(expression, power);
    }