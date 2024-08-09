public static <T> MutableList<T> select(T[] objectArray, Predicate<? super T> predicate)
    {
        if (objectArray == null)
        {
            throw new IllegalArgumentException("Cannot perform a select on null");
        }

        return ArrayIterate.select(objectArray, predicate, FastList.<T>newList());
    }