public static <T> Collection<T> select(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        if (iterable instanceof MutableCollection)
        {
            return ((MutableCollection<T>) iterable).select(predicate);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.select((ArrayList<T>) iterable, predicate);
        }
        if (iterable instanceof List)
        {
            return ListIterate.select((List<T>) iterable, predicate);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.select(
                    iterable,
                    predicate,
                    DefaultSpeciesNewStrategy.INSTANCE.speciesNew((Collection<T>) iterable));
        }
        if (iterable != null)
        {
            return IterableIterate.select(iterable, predicate);
        }
        throw new IllegalArgumentException("Cannot perform a select on null");
    }