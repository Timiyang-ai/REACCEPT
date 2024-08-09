public void destroy() {
        this.smallBlockCacheMap.destroy();
        this.txCacheMap.destroy();
    }