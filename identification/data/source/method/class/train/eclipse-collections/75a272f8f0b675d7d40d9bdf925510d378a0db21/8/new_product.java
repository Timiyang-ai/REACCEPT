public static <K, V> int count(Map<K, V> map, Predicate<? super V> predicate)
    {
        CountProcedure<V> procedure = new CountProcedure<>(predicate);
        MapIterate.forEachValue(map, procedure);
        return procedure.getCount();
    }