@Override
    public Result commitTx(Transaction tx, Object secondaryData) {
        TransactionManager.getProcessorList(tx.getClass());
        return null;
    }