@Override
    public Result broadcastTx(Transaction tx) {
        try {
            ValidateResult validateResult = contractService.baseValidate(tx);
            if(validateResult.isFailed()) {
                return validateResult;
            }
        } catch (NulsException e) {
            Log.error(e);
            return Result.getFailed();
        }

        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);
        consensusService.newTx(tx);
        // pierre test comment out
        //return Result.getSuccess();
        return messageBusService.broadcast(message, null, true, 50);
    }