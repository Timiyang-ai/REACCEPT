@Test
    public void testToString() throws Exception {
        int keyCnt = 4;

        for (int i = 1; i <= keyCnt; i++)
            cache1.put(new CacheKey(i), new CacheValue(String.valueOf(i)));

        // Create query with key filter.

        QueryCursor<Cache.Entry<CacheKey, CacheValue>> qry =
            cache1.query(new SqlQuery<CacheKey, CacheValue>(CacheValue.class, "val > 0"));

        assertEquals(keyCnt, qry.getAll().size());
    }