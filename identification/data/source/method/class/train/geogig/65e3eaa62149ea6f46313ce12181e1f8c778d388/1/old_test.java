    public @Test void acquire() {

        ObjectCache cache1 = cacheManager.acquire("id1");
        assertNotNull(cache1);

        ObjectCache cache2 = cacheManager.acquire("id1");
        assertSame(cache1, cache2);

        ObjectCache cache3 = cacheManager.acquire("id2");
        assertNotNull(cache3);
        assertNotSame(cache1, cache3);

    }