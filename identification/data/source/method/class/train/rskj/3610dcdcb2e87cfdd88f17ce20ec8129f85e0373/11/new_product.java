@Override
    public TransactionInfo getTransactionInfo(Keccak256 hash) {
        TransactionInfo txInfo = receiptStore.get(hash);

        if (txInfo == null) {
            return null;
        }

        Transaction tx = this.getBlockByHash(txInfo.getBlockHash()).getTransactionsList().get(txInfo.getIndex());
        txInfo.setTransaction(tx);

        return txInfo;
    }