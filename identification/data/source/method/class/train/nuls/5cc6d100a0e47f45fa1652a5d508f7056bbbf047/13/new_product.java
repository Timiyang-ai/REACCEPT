@Override
    public Result commitTx(Transaction tx, Object secondaryData) {
        List<TransactionProcessor> processorList = TransactionManager.getProcessorList(tx.getClass());
        List<TransactionProcessor> commitedProcessorList = new ArrayList<>();
        for (TransactionProcessor processor : processorList) {
            Result result = processor.onCommit(tx, secondaryData);
            if (result.isSuccess()) {
                commitedProcessorList.add(processor);
            } else {
                for (int i = commitedProcessorList.size() - 1; i >= 0; i--) {
                    TransactionProcessor processor1 = commitedProcessorList.get(i);
                    processor1.onRollback(tx, secondaryData);
                }
                return result;
            }
        }
        return Result.getSuccess();
    }