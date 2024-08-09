public static <T> Collection<T> drop(Iterable<T> list, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return IterableIterate.drop(list, count, FastList.<T>newList());
    }