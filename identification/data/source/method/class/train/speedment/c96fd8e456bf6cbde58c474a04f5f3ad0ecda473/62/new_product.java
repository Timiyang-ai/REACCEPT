public static <T> ToDouble<T> multiply(ToFloat<T> first, ToLong<T> second) {
        return MultiplyUtil.floatMultiplyLong(first, second);
    }