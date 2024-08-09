@SuppressWarnings("unchecked") // removeAll() always creates an array of the same type as its input
    public static <T> T[] removeAll(T[] array, int... indices) {
        return (T[]) removeAll((Object) array, clone(indices));
    }