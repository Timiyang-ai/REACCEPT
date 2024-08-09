    @Test
    public void forEachInBoth()
    {
        MutableMap<String, String> map = UnifiedMap.newMap();
        ArrayIterate.forEachInBoth(
                new String[]{"1", "2", "3"},
                new String[]{"a", "b", "c"},
                new MapPutProcedure<>(map));
        Assert.assertEquals(
                UnifiedMap.newWithKeysValues(
                        "1", "a",
                        "2", "b",
                        "3", "c"),
                map);
        ArrayIterate.forEachInBoth(null, null, (argument1, argument2) -> Assert.fail());
    }