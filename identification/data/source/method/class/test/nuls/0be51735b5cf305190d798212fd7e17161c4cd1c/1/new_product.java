public void cacheSendedMessage(BaseMessage messgae) {
        this.cacheMapSended.put(messgae.getHash(), messgae);
    }