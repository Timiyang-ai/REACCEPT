@Test
    public void testInstantiateMapCache() {
        requestService.startRequest();

        MapCache cache = cachingService.instantiateMapCache("aaronz-map", null);
        assertNotNull(cache);
        assertEquals("aaronz-map", cache.getName());
        assertNotNull(cache.getCache());
        assertEquals(0, cache.size());
        assertNotNull(cache.getConfig());
        assertEquals(cache.getConfig().getCacheScope(), CacheScope.REQUEST);

        MapCache cache2 = cachingService.instantiateMapCache("aaronz-map", null);
        assertNotNull(cache2);
        assertEquals(cache2, cache);

        requestService.endRequest(null);
    }