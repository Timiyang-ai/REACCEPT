public static <T, IV> Collection<T> selectWith(
            Iterable<T> iterable,
            Predicate2<? super T, ? super IV> predicate,
            IV parameter)
    {
        if (iterable instanceof MutableCollection)
        {
            return ((MutableCollection<T>) iterable).selectWith(predicate, parameter);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.selectWith((ArrayList<T>) iterable, predicate, parameter);
        }
        if (iterable instanceof List)
        {
            return ListIterate.selectWith((List<T>) iterable, predicate, parameter);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.selectWith(
                    iterable,
                    predicate,
                    parameter,
                    DefaultSpeciesNewStrategy.INSTANCE.speciesNew((Collection<T>) iterable));
        }
        if (iterable != null)
        {
            return IterableIterate.selectWith(iterable, predicate, parameter, FastList.newList());
        }
        throw new IllegalArgumentException("Cannot perform a selectWith on null");
    }