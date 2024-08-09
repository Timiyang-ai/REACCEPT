public static <T, A> ArrayList<A> collect(
            ArrayList<T> list,
            Function<? super T, ? extends A> function)
    {
        return ArrayListIterate.collect(list, function, new ArrayList<A>(list.size()));
    }