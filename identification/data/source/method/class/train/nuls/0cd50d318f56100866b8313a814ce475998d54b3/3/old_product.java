public SmallBlock getSmallBlock(NulsDigestData hash) {
        if (null == smallBlockCacheMap) {
            return null;
        }
        return smallBlockCacheMap.get(hash);
    }