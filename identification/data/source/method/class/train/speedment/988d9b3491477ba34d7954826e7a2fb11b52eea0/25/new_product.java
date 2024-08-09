public static <T> ToDouble<T> pow(ToByte<T> expression, ToDouble<T> power) {
        return PowUtil.bytePowDouble(expression, power);
    }