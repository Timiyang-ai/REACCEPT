@Override
    public TransactionInfo getTransactionInfo(byte[] hash) {
        TransactionInfo txInfo = receiptStore.get(hash);

        if (txInfo == null) {
            return null;
        }

        Transaction tx = this.getBlockByHash(txInfo.getBlockHash()).getTransactionsList().get(txInfo.getIndex());
        txInfo.setTransaction(tx);

        return txInfo;
    }