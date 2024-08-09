public static <T> MutableList<T> select(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        return IterableIterate.select(iterable, predicate, FastList.newList());
    }