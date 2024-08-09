@Override
    public Result broadcastTx(Transaction tx) {
        temporaryCacheManager.cacheTx(tx);
        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);
        messageBusService.receiveMessage(message,null);
        return messageBusService.broadcastAndCache(message, null, true);
    }