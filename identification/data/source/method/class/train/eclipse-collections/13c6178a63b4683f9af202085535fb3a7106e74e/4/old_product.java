public static <T> Collection<T> selectInstancesOf(Iterable<?> iterable, Class<T> clazz)
    {
        if (iterable instanceof MutableCollection)
        {
            return ((MutableCollection<?>) iterable).selectInstancesOf(clazz);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.selectInstancesOf((ArrayList<?>) iterable, clazz);
        }
        if (iterable instanceof List)
        {
            return ListIterate.selectInstancesOf((List<?>) iterable, clazz);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.selectInstancesOf(
                    iterable,
                    clazz,
                    DefaultSpeciesNewStrategy.INSTANCE.<T>speciesNew((Collection<?>) iterable));
        }
        if (iterable != null)
        {
            return IterableIterate.selectInstancesOf(iterable, clazz);
        }
        throw new IllegalArgumentException("Cannot perform a selectInstancesOf on null");
    }