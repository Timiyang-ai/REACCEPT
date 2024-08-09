public static <T> ToDouble<T> pow(ToInt<T> expression, ToDouble<T> power) {
        return PowUtil.intPowDouble(expression, power);
    }