public static <T> Collection<T> take(Iterable<T> iterable, int count)
    {
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.take((ArrayList<T>) iterable, count);
        }
        if (iterable instanceof RandomAccess)
        {
            return RandomAccessListIterate.take((List<T>) iterable, count);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.take(
                    iterable,
                    count,
                    DefaultSpeciesNewStrategy.INSTANCE.<T>speciesNew((Collection<T>) iterable, count));
        }
        if (iterable != null)
        {
            return IterableIterate.take(iterable, count);
        }
        throw new IllegalArgumentException("Cannot perform a take on null");
    }