public boolean cacheTx(Transaction tx) {
        return txCacheMap.put(tx.getHash(), tx);
    }