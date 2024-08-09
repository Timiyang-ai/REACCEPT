public boolean kownTheMessage(String hashHex) {
        boolean b = this.cacheMapRecieved.containsKey(hashHex) ||
                this.cacheMapSended.containsKey(hashHex);
        return b;
    }