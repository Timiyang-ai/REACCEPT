public static <T, A> MutableList<A> collectIf(
            List<T> list,
            Predicate<? super T> predicate,
            Function<? super T, ? extends A> function)
    {
        return RandomAccessListIterate.collectIf(list, predicate, function, FastList.newList());
    }