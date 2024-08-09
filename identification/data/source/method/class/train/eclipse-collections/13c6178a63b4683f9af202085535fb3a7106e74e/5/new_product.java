public static <T> MutableList<T> distinct(T[] objectArray)
    {
        return ArrayIterate.distinct(objectArray, FastList.newList());
    }