public static <T> ToDouble<T> pow(ToInt<T> expression, double power) {
        return PowUtil.intPowDouble(expression, power);
    }