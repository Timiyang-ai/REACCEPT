public static <T> T[] clone(final T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }