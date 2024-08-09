public static <T, IV> ArrayList<T> rejectWith(
            ArrayList<T> list,
            Predicate2<? super T, ? super IV> predicate,
            IV injectedValue)
    {
        return ArrayListIterate.rejectWith(list, predicate, injectedValue, new ArrayList<T>());
    }