public static <T> ToDouble<T> pow(ToInt<T> expression, ToInt<T> power) {
        return PowUtil.intPowInt(expression, power);
    }