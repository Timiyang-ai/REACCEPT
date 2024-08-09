public void cacheSmallBlock(NulsDigestData requestHash, SmallBlock smallBlock) {
        smallBlockCacheMap.put(requestHash, smallBlock);
    }