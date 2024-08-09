@Override
    public Result rollback(Transaction tx, Object secondaryData) {
        List<TransactionProcessor> processorList = TransactionManager.getProcessorList(tx.getClass());
        List<TransactionProcessor> rollbackedList = new ArrayList<>();
        for (TransactionProcessor processor : processorList) {
            Result result = processor.onRollback(tx, secondaryData);
            if (result.isSuccess()) {
                rollbackedList.add(processor);
            } else {
                for (int i = rollbackedList.size() - 1; i >= 0; i--) {
                    TransactionProcessor processor1 = rollbackedList.get(i);
                    processor1.onCommit(tx, secondaryData);
                }
                return result;
            }
        }
        return Result.getSuccess();
    }