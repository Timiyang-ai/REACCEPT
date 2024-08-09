@Test
    public void testExtractSubMap(){
        assertEquals(Collections.emptyMap(), MapUtil.extractSubMap(null, "id"));
    }