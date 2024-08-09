public static <T> MutableList<T> reject(T[] objectArray, Predicate<? super T> predicate)
    {
        return ArrayIterate.reject(objectArray, predicate, FastList.newList());
    }