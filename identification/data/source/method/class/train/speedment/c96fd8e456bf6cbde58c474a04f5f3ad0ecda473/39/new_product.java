public static <T> ToDouble<T> multiply(ToDouble<T> first, ToLong<T> second) {
        return MultiplyUtil.doubleMultiplyLong(first, second);
    }