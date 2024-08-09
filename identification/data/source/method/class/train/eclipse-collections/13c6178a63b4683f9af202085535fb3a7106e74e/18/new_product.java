public static <T, IV> MutableList<T> rejectWith(
            List<T> list,
            Predicate2<? super T, ? super IV> predicate,
            IV injectedValue)
    {
        return ListIterate.rejectWith(list, predicate, injectedValue, FastList.newList());
    }