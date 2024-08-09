public static <T> ToDouble<T> pow(ToInt<T> expression, int power) {
        return PowUtil.intPowInt(expression, power);
    }