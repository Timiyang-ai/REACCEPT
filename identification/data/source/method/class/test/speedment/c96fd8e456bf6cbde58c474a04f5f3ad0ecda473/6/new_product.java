public static <T> ToLong<T> plus(ToByte<T> first, long second) {
        return PlusUtil.bytePlusLong(first, second);
    }