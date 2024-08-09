@Test
    public void getSendMessage() {
        if (null == messageCacheService.getSendMessage(blockMessage.getHash().getDigestHex())){
            messageCacheService.cacheSendedMessage(blockMessage);
        }
        assertNotNull(messageCacheService.getSendMessage(blockMessage.getHash().getDigestHex()));
    }