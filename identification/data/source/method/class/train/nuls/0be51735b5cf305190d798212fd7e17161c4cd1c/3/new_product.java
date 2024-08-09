public BaseMessage getSendMessage(NulsDigestData hash) {
        return (BaseMessage) this.cacheMapSended.get(hash);
    }