public static <T> ToDouble<T> pow(ToByte<T> expression, ToInt<T> power) {
        return PowUtil.bytePowInt(expression, power);
    }