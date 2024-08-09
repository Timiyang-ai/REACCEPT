public static <T, IV> MutableList<T> selectWith(
            List<T> list,
            Predicate2<? super T, ? super IV> predicate,
            IV injectedValue)
    {
        return ListIterate.selectWith(list, predicate, injectedValue, FastList.<T>newList());
    }