public static <T, A> MutableList<A> collect(
            List<T> list,
            Function<? super T, ? extends A> function)
    {
        return RandomAccessListIterate.collect(list, function, FastList.newList(list.size()));
    }