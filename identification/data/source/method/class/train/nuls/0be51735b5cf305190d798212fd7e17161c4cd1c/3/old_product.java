public BaseMessage getSendMessage(String hashHex) {
        return (BaseMessage) this.cacheMapSended.get(hashHex);
    }