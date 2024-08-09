public static <T> ToDouble<T> plus(ToFloat<T> first, long second) {
        return PlusUtil.floatPlusLong(first, second);
    }