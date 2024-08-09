@Test
    public void kownTheMessage() {
        if (null == messageCacheService.getSendMessage(blockMessage.getHash())) {
            messageCacheService.cacheSendedMessage(blockMessage);
            assertTrue(messageCacheService.kownTheMessage(blockMessage.getHash()));
        } else {
            assertTrue(messageCacheService.kownTheMessage(blockMessage.getHash()));
        }
        messageCacheService.cacheRecievedMessageHash(blockMessage.getHash());
        assertTrue(messageCacheService.kownTheMessage(blockMessage.getHash()));
    }