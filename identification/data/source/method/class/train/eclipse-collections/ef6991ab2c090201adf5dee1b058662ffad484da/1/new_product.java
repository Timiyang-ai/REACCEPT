public static <T> Optional<T> detectOptional(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        if (iterable instanceof RichIterable)
        {
            return ((RichIterable<T>) iterable).detectOptional(predicate);
        }
        if (iterable instanceof ArrayList)
        {
            return ArrayListIterate.detectOptional((ArrayList<T>) iterable, predicate);
        }
        if (iterable instanceof RandomAccess)
        {
            return RandomAccessListIterate.detectOptional((List<T>) iterable, predicate);
        }
        if (iterable != null)
        {
            return IterableIterate.detectOptional(iterable, predicate);
        }
        throw new IllegalArgumentException("Cannot perform detectOptional on null");
    }