public static <K, V> MutableList<V> select(Map<K, V> map, Predicate<? super V> predicate)
    {
        return MapIterate.select(map, predicate, FastList.newList());
    }