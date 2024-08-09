public static <T> ToDouble<T> pow(ToDouble<T> expression, double power) {
        return PowUtil.doublePowDouble(expression, power);
    }