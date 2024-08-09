public static <K, V> MutableList<V> reject(Map<K, V> map, Predicate<? super V> predicate)
    {
        return MapIterate.reject(map, predicate, FastList.<V>newList());
    }