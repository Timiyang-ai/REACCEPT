public static <T> MutableList<T> take(T[] array, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return ArrayIterate.take(array, count, FastList.<T>newList(Math.min(array.length, count)));
    }