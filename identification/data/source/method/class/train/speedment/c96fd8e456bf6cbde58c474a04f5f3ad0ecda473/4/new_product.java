public static <T> ToLong<T> minus(ToShort<T> first, long second) {
        return MinusUtil.shortMinusLong(first, second);
    }