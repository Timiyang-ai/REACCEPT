public static TransactionViewModel fromHash(Tangle tangle, final Hash hash) throws Exception {
        TransactionViewModel transactionViewModel = new TransactionViewModel(
                (Transaction) tangle.load(Transaction.class, hash), hash);
        fillMetadata(tangle, transactionViewModel);
        return transactionViewModel;
    }