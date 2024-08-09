public static <T, V> Collection<V> collect(
            Iterable<T> iterable,
            Function<? super T, ? extends V> function)
    {
        if (iterable instanceof MutableCollection)
        {
            return ((MutableCollection<T>) iterable).collect(function);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.collect((ArrayList<T>) iterable, function);
        }
        if (iterable instanceof RandomAccess)
        {
            return RandomAccessListIterate.collect((List<T>) iterable, function);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.collect(
                    iterable,
                    function,
                    DefaultSpeciesNewStrategy.INSTANCE.speciesNew(
                            (Collection<T>) iterable,
                            ((Collection<T>) iterable).size()));
        }
        if (iterable != null)
        {
            return IterableIterate.collect(iterable, function);
        }
        throw new IllegalArgumentException("Cannot perform a collect on null");
    }