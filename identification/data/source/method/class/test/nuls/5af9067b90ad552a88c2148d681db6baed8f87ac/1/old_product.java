public void cacheSmallBlock(SmallBlock newBlock) {
        smallBlockCacheMap.put(newBlock.getHeader().getHash(), newBlock);
    }