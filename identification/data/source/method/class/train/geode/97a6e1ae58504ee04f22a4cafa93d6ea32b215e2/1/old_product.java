final Cache getCache() {
    if (this.cache != null) {
      boolean isReconnecting = this.cache.isReconnecting();
      if (isReconnecting) {
        Cache newCache = this.cache.getReconnectedCache();
        if (newCache != null) {
          this.cache = newCache;
        }
      }
    }
    return this.cache;
  }