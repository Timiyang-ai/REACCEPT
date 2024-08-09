@Test
    public void testGetCache() {
        // test getting ehcache from the config
        Cache cache = cachingService.getCache("org.dspace.caching.MemOnly", null);
        assertNotNull(cache);
        assertEquals("org.dspace.caching.MemOnly", cache.getName());

        // test getting ehcache from bean
        Cache sampleCache = cachingService.getCache("org.sakaiproject.caching.test.SampleCache", null);
        assertNotNull(sampleCache);
        assertEquals("org.sakaiproject.caching.test.SampleCache", sampleCache.getName());

        // test making new caches
        Cache c1 = cachingService.getCache("org.dspace.aztest", null);
        assertNotNull(c1);
        assertEquals("org.dspace.aztest", c1.getName());
        assertEquals(CacheScope.INSTANCE, c1.getConfig().getCacheScope());
        assertTrue(c1 instanceof EhcacheCache);

        requestService.startRequest();

        Cache rc1 = cachingService.getCache("org.dspace.request.cache1", new CacheConfig(CacheScope.REQUEST));
        assertNotNull(rc1);
        assertEquals("org.dspace.request.cache1", rc1.getName());
        assertEquals(CacheScope.REQUEST, rc1.getConfig().getCacheScope());
        assertTrue(rc1 instanceof MapCache);

        requestService.endRequest(null);

        // try getting the same one twice
        Cache c2 = cachingService.getCache("org.dspace.aztest", null);
        assertNotNull(c2);
        assertEquals(c1, c2);

    }