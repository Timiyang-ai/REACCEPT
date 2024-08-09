    @Test
    public void parseString_whenNEVER() {
        CacheDeserializedValues cacheDeserializedValues = CacheDeserializedValues.parseString("NEVER");
        assertEquals(CacheDeserializedValues.NEVER, cacheDeserializedValues);
    }