public void requestTransaction(Hash hash) {
        if (!snapshotProvider.getInitialSnapshot().hasSolidEntryPoint(hash)) {
            synchronized (syncObj) {
                if (transactionsToRequestIsFull()) {
                    popEldestTransactionToRequest();
                }
                transactionsToRequest.add(hash);
            }
        }
    }