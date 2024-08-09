public boolean kownTheMessage(NulsDigestData hash) {
        boolean b = this.cacheMapRecieved.containsKey(hash) ||
                this.cacheMapSended.containsKey(hash);
        return b;
    }