public static TransactionViewModel fromHash(Tangle tangle, final Hash hash) throws Exception {
        Cache<Indexable, TransactionViewModel> cache = tangle.getCache(TransactionViewModel.class);
        TransactionViewModel transactionViewModel = null;
        if (cache != null) {
            transactionViewModel = cache.get(hash);
            if (transactionViewModel != null) {
                fillMetadata(tangle, transactionViewModel);
                cachePut(tangle, transactionViewModel, hash);
                return transactionViewModel;
            }
        }

        transactionViewModel = new TransactionViewModel((Transaction) tangle.load(Transaction.class, hash), hash);
        fillMetadata(tangle, transactionViewModel);

        if (cache != null) {
            cachePut(tangle, transactionViewModel, hash);
        }
        return transactionViewModel;
    }