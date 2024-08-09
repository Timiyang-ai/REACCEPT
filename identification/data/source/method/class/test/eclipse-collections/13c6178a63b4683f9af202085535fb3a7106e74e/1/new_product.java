public static <T, P> MutableList<T> rejectWith(
            T[] objectArray,
            Predicate2<? super T, P> predicate,
            P parameter)
    {
        if (objectArray == null)
        {
            throw new IllegalArgumentException("Cannot perform a rejectWith on null");
        }
        return ArrayIterate.rejectWith(
                objectArray,
                predicate,
                parameter,
                FastList.newList());
    }