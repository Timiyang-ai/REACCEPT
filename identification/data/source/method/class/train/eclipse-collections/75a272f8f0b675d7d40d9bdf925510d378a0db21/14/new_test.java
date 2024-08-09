    @Test
    public void forEachKeyValue()
    {
        MutableMap<String, Integer> map = UnifiedMap.newMap();
        map.putAll(UnifiedMap.newMap(this.getIntegerMap()));
        MutableMap<String, Integer> newMap = UnifiedMap.newMap();
        MapPutProcedure<String, Integer> procedure = new MapPutProcedure<>(newMap);
        MapIterate.forEachKeyValue(map, procedure);
        Verify.assertMapsEqual(map, newMap);
    }