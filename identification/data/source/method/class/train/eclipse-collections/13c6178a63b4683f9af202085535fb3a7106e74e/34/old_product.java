public static <T, P> Collection<T> rejectWith(
            Iterable<T> iterable,
            Predicate2<? super T, ? super P> predicate,
            P parameter)
    {
        if (iterable instanceof MutableCollection)
        {
            return ((MutableCollection<T>) iterable).rejectWith(predicate, parameter);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.rejectWith((ArrayList<T>) iterable, predicate, parameter);
        }
        if (iterable instanceof List)
        {
            return ListIterate.rejectWith((List<T>) iterable, predicate, parameter);
        }
        if (iterable instanceof Collection)
        {
            return IterableIterate.rejectWith(
                    iterable,
                    predicate,
                    parameter,
                    DefaultSpeciesNewStrategy.INSTANCE.<T>speciesNew((Collection<T>) iterable));
        }
        if (iterable != null)
        {
            return IterableIterate.rejectWith(iterable, predicate, parameter, FastList.<T>newList());
        }
        throw new IllegalArgumentException("Cannot perform a rejectWith on null");
    }