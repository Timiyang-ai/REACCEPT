@Test
    public void removeSendedMessage() {
        if (null == messageCacheService.getSendMessage(blockMessage.getHash().getDigestHex())){
            messageCacheService.cacheSendedMessage(blockMessage);
        }
        assertNotNull(messageCacheService.getSendMessage(blockMessage.getHash().getDigestHex()));
        messageCacheService.removeSendedMessage(blockMessage);
        assertNull(messageCacheService.getSendMessage(blockMessage.getHash().getDigestHex()));
    }