public static <T> ToLong<T> minus(ToByte<T> first, long second) {
        return MinusUtil.byteMinusLong(first, second);
    }