public static <T, P> MutableList<T> selectWith(
            T[] objectArray,
            Predicate2<? super T, P> predicate,
            P parameter)
    {
        if (objectArray == null)
        {
            throw new IllegalArgumentException("Cannot perform a selectWith on null");
        }
        return ArrayIterate.selectWith(objectArray, predicate, parameter, FastList.newList());
    }