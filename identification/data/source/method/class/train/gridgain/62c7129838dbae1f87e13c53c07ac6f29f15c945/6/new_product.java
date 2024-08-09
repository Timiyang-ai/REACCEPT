private void globalRemoveAll(boolean async) throws Exception {
        IgniteCache<String, Integer> cache = jcache();

        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);

        checkSize(F.asSet("key1", "key2", "key3"));

        if (async)
            cache.removeAllAsync(F.asSet("key1", "key2")).get();
        else
            cache.removeAll(F.asSet("key1", "key2"));

        checkSize(F.asSet("key3"));

        checkContainsKey(false, "key1");
        checkContainsKey(false, "key2");
        checkContainsKey(true, "key3");

        // Put values again.
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);

        if (async)
            jcache(gridCount() > 1 ? 1 : 0).removeAllAsync().get();
        else
            jcache(gridCount() > 1 ? 1 : 0).removeAll();

        assertEquals(0, cache.localSize());
        long entryCnt = hugeRemoveAllEntryCount();

        for (int i = 0; i < entryCnt; i++)
            cache.put(String.valueOf(i), i);

        for (int i = 0; i < entryCnt; i++)
            assertEquals(Integer.valueOf(i), cache.get(String.valueOf(i)));

        if (async)
            cache.removeAllAsync().get();
        else
            cache.removeAll();

        for (int i = 0; i < entryCnt; i++)
            assertNull(cache.get(String.valueOf(i)));
    }