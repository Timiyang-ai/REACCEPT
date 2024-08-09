public static <T> ToLong<T> multiply(ToLong<T> first, ToInt<T> second) {
        return MultiplyUtil.longMultiplyInt(first, second);
    }