public static <T> MutableList<T> take(List<T> list, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        if (list instanceof RandomAccess)
        {
            return RandomAccessListIterate.take(list, count, FastList.<T>newList(Math.min(list.size(), count)));
        }
        return ListIterate.take(list, count, FastList.<T>newList());
    }