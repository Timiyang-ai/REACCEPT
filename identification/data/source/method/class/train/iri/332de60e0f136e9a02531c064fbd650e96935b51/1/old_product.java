public void requestTransaction(Hash hash) throws Exception {
        if (!snapshotProvider.getInitialSnapshot().hasSolidEntryPoint(hash) && !TransactionViewModel.exists(tangle, hash)) {
            synchronized (syncObj) {
                if (transactionsToRequestIsFull()) {
                    popEldestTransactionToRequest();
                }
                transactionsToRequest.add(hash);
            }
        }
    }