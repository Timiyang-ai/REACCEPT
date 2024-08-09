@Override
    public Result broadcastTx(Transaction tx) {
        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);
        return messageBusService.broadcastAndCache(message, null, false);
    }