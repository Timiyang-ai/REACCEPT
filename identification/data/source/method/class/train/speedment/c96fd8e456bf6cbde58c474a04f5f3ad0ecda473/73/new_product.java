public static <T> ToDouble<T> multiply(ToFloat<T> first, long second) {
        return MultiplyUtil.floatMultiplyLong(first, second);
    }