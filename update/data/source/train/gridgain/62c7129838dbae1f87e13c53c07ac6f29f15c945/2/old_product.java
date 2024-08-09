protected void globalClearAll(boolean async, boolean oldAsync) throws Exception {
        // Save entries only on their primary nodes. If we didn't do so, clearLocally() will not remove all entries
        // because some of them were blocked due to having readers.
        for (int i = 0; i < gridCount(); i++) {
            for (String key : primaryKeysForCache(jcache(i), 3, 100_000))
                jcache(i).put(key, 1);
        }

        if (async) {
            if(oldAsync) {
                IgniteCache<String, Integer> asyncCache = jcache().withAsync();

                asyncCache.clear();

                asyncCache.future().get();
            } else
                jcache().clearAsync().get();
        }
        else
            jcache().clear();

        for (int i = 0; i < gridCount(); i++)
            assert jcache(i).localSize() == 0;
    }