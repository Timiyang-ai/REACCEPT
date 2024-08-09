@Override
    public Result broadcastTx(Transaction tx) {
        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);
        consensusService.newTx(tx);
        // pierre test comment out
        //return Result.getSuccess();
        return messageBusService.broadcast(message, null, true, 50);
    }