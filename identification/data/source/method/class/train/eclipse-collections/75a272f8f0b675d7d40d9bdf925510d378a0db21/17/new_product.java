public static <T> ArrayList<T> reject(ArrayList<T> list, Predicate<? super T> predicate)
    {
        return ArrayListIterate.reject(list, predicate, new ArrayList<>());
    }