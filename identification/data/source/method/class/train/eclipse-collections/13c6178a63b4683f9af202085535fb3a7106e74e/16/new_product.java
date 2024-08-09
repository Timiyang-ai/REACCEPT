public static <T> MutableList<T> distinct(List<T> list)
    {
        return RandomAccessListIterate.distinct(list, FastList.newList());
    }