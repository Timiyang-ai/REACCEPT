public static <T> ToDouble<T> plus(ToDouble<T> first, ToLong<T> second) {
        return PlusUtil.doublePlusLong(first, second);
    }