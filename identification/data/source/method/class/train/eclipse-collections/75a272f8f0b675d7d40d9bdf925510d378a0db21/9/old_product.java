public static <T, A> ArrayList<A> flatCollect(
            ArrayList<T> list,
            Function<? super T, ? extends Iterable<A>> function)
    {
        return ArrayListIterate.flatCollect(list, function, new ArrayList<A>(list.size()));
    }