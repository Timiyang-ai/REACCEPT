public SmallBlock getSmallBlock(NulsDigestData requestHash) {
        if (null == smallBlockCacheMap) {
            return null;
        }
        return smallBlockCacheMap.get(requestHash);
    }