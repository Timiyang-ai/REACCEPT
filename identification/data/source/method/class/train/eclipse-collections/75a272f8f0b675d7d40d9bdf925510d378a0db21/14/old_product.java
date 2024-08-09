public static <K, V> void forEachKeyValue(Map<K, V> map, Procedure2<? super K, ? super V> procedure)
    {
        if (map == null)
        {
            throw new IllegalArgumentException("Cannot perform a forEachKeyValue on null");
        }

        if (MapIterate.notEmpty(map))
        {
            if (map instanceof UnsortedMapIterable)
            {
                ((MapIterable<K, V>) map).forEachKeyValue(procedure);
            }
            else
            {
                IterableIterate.forEach(map.entrySet(), new MapEntryToProcedure2<K, V>(procedure));
            }
        }
    }