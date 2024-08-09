@Test
    public void removeSendedMessage() {
        if (null == messageCacheService.getSendMessage(blockMessage.getHash())) {
            messageCacheService.cacheSendedMessage(blockMessage);
        }
        assertNotNull(messageCacheService.getSendMessage(blockMessage.getHash()));
        messageCacheService.removeSendedMessage(blockMessage);
        assertNull(messageCacheService.getSendMessage(blockMessage.getHash()));
    }