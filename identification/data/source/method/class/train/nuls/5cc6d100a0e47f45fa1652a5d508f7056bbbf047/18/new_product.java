@Override
    public Result broadcastTx(Transaction tx) {

        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);

        consensusService.newTx(tx);
        temporaryCacheManager.cacheTx(tx);

        return messageBusService.broadcast(message, null, true);
    }