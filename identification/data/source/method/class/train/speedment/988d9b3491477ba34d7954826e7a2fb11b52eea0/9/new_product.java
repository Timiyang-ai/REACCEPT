public static <T> ToDouble<T> pow(ToDouble<T> expression, ToInt<T> power) {
        return PowUtil.doublePowInt(expression, power);
    }