public static <T> MutableList<T> drop(T[] array, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return ArrayIterate.drop(array, count, FastList.newList(array.length - Math.min(array.length, count)));
    }