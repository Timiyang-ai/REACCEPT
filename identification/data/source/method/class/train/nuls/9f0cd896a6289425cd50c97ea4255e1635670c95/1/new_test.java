    @Test
    public void cacheTx() {
        Transaction tx = new CacheTestTx();
        tx.setTime(1234567654L);
        try {
            tx.setHash(NulsDigestData.calcDigestData(tx.serializeForHash()));
        } catch (IOException e) {
            Log.error(e);
        }
        manager.cacheTx(tx);
        assertTrue(true);

        getTx(tx.getHash(), tx);
    }