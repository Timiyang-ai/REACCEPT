public void removeSendedMessage(BaseMessage message) {
        this.cacheMapSended.remove(message.getHash());
    }