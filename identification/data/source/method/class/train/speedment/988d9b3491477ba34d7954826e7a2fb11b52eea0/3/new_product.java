public static <T> ToDouble<T> pow(ToByte<T> expression, double power) {
        return PowUtil.bytePowDouble(expression, power);
    }