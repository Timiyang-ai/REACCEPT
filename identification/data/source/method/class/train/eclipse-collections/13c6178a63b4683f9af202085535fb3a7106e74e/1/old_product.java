public static <T> MutableList<T> drop(List<T> list, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        if (list instanceof RandomAccess)
        {
            return RandomAccessListIterate.drop(list, count, FastList.<T>newList(list.size() - Math.min(list.size(), count)));
        }
        return ListIterate.drop(list, count, FastList.<T>newList());
    }