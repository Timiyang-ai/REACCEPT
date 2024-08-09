public static <T, A> MutableList<A> collectIf(
            List<T> list,
            Predicate<? super T> predicate,
            Function<? super T, ? extends A> function)
    {
        return ListIterate.collectIf(list, predicate, function, FastList.<A>newList());
    }