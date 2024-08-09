public static <T> Collection<T> drop(Iterable<T> iterable, int count)
    {
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.drop((ArrayList<T>) iterable, count);
        }
        if (iterable instanceof RandomAccess)
        {
            return RandomAccessListIterate.drop((List<T>) iterable, count);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.drop(
                    iterable,
                    count,
                    DefaultSpeciesNewStrategy.INSTANCE.speciesNew((Collection<T>) iterable, count));
        }
        if (iterable != null)
        {
            return IterableIterate.drop(iterable, count);
        }
        throw new IllegalArgumentException("Cannot perform a drop on null");
    }