public static <T, V> MutableList<V> collect(
            T[] objectArray,
            Function<? super T, ? extends V> function)
    {
        if (objectArray == null)
        {
            throw new IllegalArgumentException("Cannot perform a collect on null");
        }
        return ArrayIterate.collect(objectArray, function, FastList.newList(objectArray.length));
    }