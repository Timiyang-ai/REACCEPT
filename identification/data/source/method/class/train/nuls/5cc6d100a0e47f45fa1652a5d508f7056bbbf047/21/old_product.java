@Override
    public Result broadcastTx(Transaction tx) {
        temporaryCacheManager.cacheTx(tx);
        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);
        return messageBusService.broadcastAndCache(message, null, true);
    }