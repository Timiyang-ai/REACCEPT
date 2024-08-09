public static <T, V> MutableList<V> collect(
            Iterable<T> iterable,
            Function<? super T, ? extends V> function)
    {
        return IterableIterate.collect(iterable, function, FastList.<V>newList());
    }