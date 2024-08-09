    @Test
    public void popEldestTransactionToRequest() throws Exception {
        TransactionRequester txReq = new TransactionRequester(tangle, snapshotProvider);
        // Add some Txs to the pool and see if the method pops the eldest one
        Hash eldest = getTransactionHash();
        txReq.requestTransaction(eldest);
        txReq.requestTransaction(getTransactionHash());
        txReq.requestTransaction(getTransactionHash());
        txReq.requestTransaction(getTransactionHash());

        txReq.popEldestTransactionToRequest();
        // Check that the transaction is there no more
        assertFalse(txReq.isTransactionRequested(eldest));
    }