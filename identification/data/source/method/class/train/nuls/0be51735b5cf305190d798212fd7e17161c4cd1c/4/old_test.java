@Test
    public void cacheRecievedMessageHash() throws Exception {
        messageCacheService.cacheRecievedMessageHash(blockMessage.getHash().getDigestHex());
        assertNotNull(messageCacheService);

        Field cacheMapRecievedField = messageCacheService.getClass().getDeclaredField("cacheMapRecieved");
        cacheMapRecievedField.setAccessible(true);
        CacheMap<String, Integer>  cacheMap = (CacheMap<String, Integer>)cacheMapRecievedField.get(messageCacheService);
        assertTrue(cacheMap.get(blockMessage.getHash().getDigestHex()) == 1);
    }