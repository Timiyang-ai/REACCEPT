public void cacheTx(Transaction tx) {
        txCacheMap.put(tx.getHash(), tx);
    }