public static <T> ToFloat<T> multiply(ToFloat<T> first, ToInt<T> second) {
        return MultiplyUtil.floatMultiplyInt(first, second);
    }