public static <T> Collection<T> reject(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        if (iterable instanceof MutableCollection)
        {
            return ((MutableCollection<T>) iterable).reject(predicate);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.reject((ArrayList<T>) iterable, predicate);
        }
        if (iterable instanceof List)
        {
            return ListIterate.reject((List<T>) iterable, predicate);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.reject(
                    iterable,
                    predicate,
                    DefaultSpeciesNewStrategy.INSTANCE.<T>speciesNew((Collection<T>) iterable));
        }
        if (iterable != null)
        {
            return IterableIterate.reject(iterable, predicate);
        }
        throw new IllegalArgumentException("Cannot perform a reject on null");
    }