public static <T> MutableList<T> drop(List<T> list, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return RandomAccessListIterate.drop(list, count, FastList.newList(list.size() - Math.min(list.size(), count)));
    }