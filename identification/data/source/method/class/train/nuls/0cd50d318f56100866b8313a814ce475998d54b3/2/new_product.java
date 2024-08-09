public SmallBlock getSmallBlockByHash(NulsDigestData blockHash) {

        return smallBlockCacheMap.get(blockHash);
    }