public static <T> ToFloat<T> plus(ToFloat<T> first, ToInt<T> second) {
        return PlusUtil.floatPlusInt(first, second);
    }