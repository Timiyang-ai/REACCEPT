public static <T, V> Collection<V> collectIf(
            Iterable<T> iterable,
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        if (iterable instanceof MutableCollection)
        {
            return ((MutableCollection<T>) iterable).collectIf(predicate, function);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.collectIf((ArrayList<T>) iterable, predicate, function);
        }
        if (iterable instanceof List)
        {
            return ListIterate.collectIf((List<T>) iterable, predicate, function);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.collectIf(
                    iterable,
                    predicate,
                    function,
                    DefaultSpeciesNewStrategy.INSTANCE.<V>speciesNew((Collection<T>) iterable));
        }
        if (iterable != null)
        {
            return IterableIterate.collectIf(iterable, predicate, function);
        }
        throw new IllegalArgumentException("Cannot perform a collectIf on null");
    }