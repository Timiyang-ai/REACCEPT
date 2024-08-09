public static <T> MutableList<T> distinct(List<T> list)
    {
        return ListIterate.distinct(list, FastList.newList());
    }