@Override
    public ValidateResult conflictDetect(List<Transaction> txList) {
        if (null == txList || txList.isEmpty()) {
            return ValidateResult.getSuccessResult();
        }
//        ValidateResult result = ledgerService.verifyDoubleSpend(txList);
//        if (result.isFailed()) {
//            return result;
//        }
        List<Transaction> newTxList = new ArrayList<>();
        for (Transaction tx : txList) {
            if (tx.getType() == ProtocolConstant.TX_TYPE_COINBASE || tx.getType() == ProtocolConstant.TX_TYPE_TRANSFER) {
                continue;
            }
            newTxList.add(tx);
        }
        List<TransactionProcessor> processorList = TransactionManager.getAllProcessorList();
        ValidateResult result = ValidateResult.getSuccessResult();
        for (TransactionProcessor processor : processorList) {
            result = processor.conflictDetect(newTxList);
            if (result.isFailed()) {
                break;
            }
        }
        return result;
    }