@Test
    public void kownTheMessage() {
        if (null == messageCacheService.getSendMessage(blockMessage.getHash().getDigestHex())){
            messageCacheService.cacheSendedMessage(blockMessage);
            assertTrue(messageCacheService.kownTheMessage(blockMessage.getHash().getDigestHex()));
        }else{
            assertTrue(messageCacheService.kownTheMessage(blockMessage.getHash().getDigestHex()));
        }
        messageCacheService.cacheRecievedMessageHash(blockMessage.getHash().getDigestHex());
        assertTrue(messageCacheService.kownTheMessage(blockMessage.getHash().getDigestHex()));
    }