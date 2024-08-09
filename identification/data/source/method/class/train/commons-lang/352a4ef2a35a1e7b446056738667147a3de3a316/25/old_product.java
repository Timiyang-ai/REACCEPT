@SuppressWarnings("unchecked") // remove() always creates an array of the same type as its input
    public static <T> T[] remove(T[] array, int index) {
        return (T[]) remove((Object) array, index);
    }