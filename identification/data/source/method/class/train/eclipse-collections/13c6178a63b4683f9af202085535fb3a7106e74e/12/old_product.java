public static <T> Collection<T> take(Iterable<T> iterable, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return IterableIterate.take(iterable, count, FastList.<T>newList());
    }