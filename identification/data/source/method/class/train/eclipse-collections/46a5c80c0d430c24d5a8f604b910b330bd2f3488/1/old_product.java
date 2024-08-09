public static <T> void forEach(Iterable<T> iterable, Procedure<? super T> procedure)
    {
        if (iterable instanceof InternalIterable)
        {
            ((InternalIterable<T>) iterable).forEach(procedure);
        }
        else if (iterable instanceof ArrayList)
        {
            ArrayListIterate.forEach((ArrayList<T>) iterable, procedure);
        }
        else if (iterable instanceof List)
        {
            ListIterate.forEach((List<T>) iterable, procedure);
        }
        else if (iterable != null)
        {
            IterableIterate.forEach(iterable, procedure);
        }
        else
        {
            throw new IllegalArgumentException("Cannot perform a forEach on null");
        }
    }