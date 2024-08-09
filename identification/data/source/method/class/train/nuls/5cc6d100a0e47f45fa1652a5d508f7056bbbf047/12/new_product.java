@Override
    public Result broadcastTx(Transaction tx) {
        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);
        consensusService.newTx(tx);
        return messageBusService.broadcast(message, null, true);
    }