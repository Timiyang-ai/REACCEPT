public static <T, V> MutableList<V> collectIf(
            Iterable<T> iterable,
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        return IterableIterate.collectIf(iterable, predicate, function, FastList.newList());
    }