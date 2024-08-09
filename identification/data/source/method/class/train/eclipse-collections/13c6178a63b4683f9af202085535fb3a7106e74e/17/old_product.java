public static <T> MutableList<T> reject(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        return IterableIterate.reject(iterable, predicate, FastList.<T>newList());
    }