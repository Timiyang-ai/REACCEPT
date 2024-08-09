@Test
    public void cacheRecievedMessageHash() throws Exception {
        messageCacheService.cacheRecievedMessageHash(blockMessage.getHash());
        assertNotNull(messageCacheService);

        Field cacheMapRecievedField = messageCacheService.getClass().getDeclaredField("cacheMapRecieved");
        cacheMapRecievedField.setAccessible(true);
        CacheMap<NulsDigestData, Integer> cacheMap = (CacheMap<NulsDigestData, Integer>) cacheMapRecievedField.get(messageCacheService);
        assertTrue(cacheMap.get(blockMessage.getHash()) == 1);
    }