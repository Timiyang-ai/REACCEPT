public static <T> ToFloat<T> minus(ToFloat<T> first, ToInt<T> second) {
        return MinusUtil.floatMinusInt(first, second);
    }