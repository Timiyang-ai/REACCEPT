public static <T, A> MutableList<A> collect(
            List<T> list,
            Function<? super T, ? extends A> function)
    {
        return RandomAccessListIterate.collect(list, function, FastList.<A>newList(list.size()));
    }