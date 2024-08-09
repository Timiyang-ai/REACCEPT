public static <T> ToLong<T> multiply(ToInt<T> first, long second) {
        return MultiplyUtil.intMultiplyLong(first, second);
    }