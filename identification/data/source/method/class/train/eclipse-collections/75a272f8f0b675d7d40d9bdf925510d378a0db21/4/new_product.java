public static <T, IV> ArrayList<T> selectWith(
            ArrayList<T> list,
            Predicate2<? super T, ? super IV> predicate,
            IV injectedValue)
    {
        return ArrayListIterate.selectWith(list, predicate, injectedValue, new ArrayList<>());
    }