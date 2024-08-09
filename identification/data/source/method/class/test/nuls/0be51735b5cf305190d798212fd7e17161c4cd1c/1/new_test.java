@Test
    public void cacheSendedMessage() {
        messageCacheService.cacheSendedMessage(blockMessage);
        assertNotNull(messageCacheService.getSendMessage(blockMessage.getHash()));
    }