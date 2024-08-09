public static <T> ArrayList<T> select(ArrayList<T> list, Predicate<? super T> predicate)
    {
        return ArrayListIterate.select(list, predicate, new ArrayList<>());
    }